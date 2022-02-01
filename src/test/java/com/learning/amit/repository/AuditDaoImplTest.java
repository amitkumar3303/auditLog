package com.learning.amit.repository;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import com.learning.amit.dto.LogEvent;

@ExtendWith(MockitoExtension.class)

/*@SpringBootTest
@ActiveProfiles("test")
@Sql(scripts = "classpath:schema.sql")*/
class AuditDaoImplTest {

	@Mock
	private  NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@InjectMocks
	private  AuditDaoImpl  auditDao;
	@Test
	void testSaveList() {
	/*
	 * int[] response = auditDao.saveList(getMockedLogEventList());
	 * assertNotNull(response); assertTrue(response.length>0);
	 */
		
	}

	
	@SuppressWarnings("unused")
	private static List<LogEvent> getMockedLogEventList(){
		List<LogEvent> logeventList=new ArrayList<>();
		LogEvent logEvent=new LogEvent();
		logEvent.setHost("host");
		logEvent.setId("Amit");
		logEvent.setState("STARTED");
		logEvent.setType("type");
		logeventList.add(logEvent);
		return logeventList;
	}
}
