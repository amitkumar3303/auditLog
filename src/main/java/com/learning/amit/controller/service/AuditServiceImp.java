package com.learning.amit.controller.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.learning.amit.dto.Location;
import com.learning.amit.dto.LogEvent;
import com.learning.amit.repository.AuditDao;

import ch.qos.logback.classic.Logger;

@Service
public class AuditServiceImp implements AuditService {


	@Autowired
	private AuditDao auditDao;
	 private static final Logger logger = (Logger) LoggerFactory.getLogger(AuditServiceImp.class);
	 static Map<String,Boolean> auditResponse=new HashMap<>();
	 static final BigInteger CHECKVALUE=BigInteger.valueOf(4);
	
	
	
	
	@Override
	public Map<String, Boolean> auditSaveLog(Location location) throws IOException {

		logger.info("Audit Service with DB Started");
		
		 logger.info("path{} ",location.getPath());
		 String path1 =	 location.getPath();
			List<LogEvent> logEventList = new ArrayList<>();
			
			try (BufferedReader br = Files.newBufferedReader(Paths.get(path1), StandardCharsets.UTF_8)) {
			    for (String line = null; (line = br.readLine()) != null;) {
			    	ObjectMapper objectMapper = new ObjectMapper();
					LogEvent log = objectMapper.readValue(line, LogEvent.class);
					logEventList.add(log);
			    }
			}
			
			ExecutorService executorService=Executors.newFixedThreadPool(2);
			
			//Save data to DB
			Runnable task1=()->{
			auditDao.saveList(logEventList);
			};
			Runnable task2=()->{
				Map<String, List<LogEvent>> eventGroupedBy = logEventList.parallelStream().collect(Collectors.groupingBy(LogEvent::getId));
				eventGroupedBy.entrySet().parallelStream().forEach(entry->{
					String key = entry.getKey();
					BigInteger maxTime = entry.getValue().parallelStream().filter(a->a.getState().equalsIgnoreCase("FINISHED")).map(a->a.getTimestamp()).max(BigInteger::compareTo).get();
					BigInteger minTime = entry.getValue().parallelStream().filter(a->a.getState().equalsIgnoreCase("STARTED")).map(a->a.getTimestamp()).min(BigInteger::compareTo).get();
					finalCalulate(key,maxTime,minTime);
				});	
				
			};
			executorService.submit(task1);
			executorService.submit(task2);
			executorService.shutdown();
			
			return auditResponse;
		
	 }
	 private static void finalCalulate(String key, BigInteger maxTime, BigInteger minTime) {
			
			auditResponse.put(key, (maxTime.subtract(minTime)).longValue()>4);
			auditResponse.put(key, !((maxTime.subtract(minTime)).longValue()<=4));
	}
	
}
