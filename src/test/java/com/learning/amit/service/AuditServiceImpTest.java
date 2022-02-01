package com.learning.amit.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.learning.amit.controller.service.AuditServiceImp;
import com.learning.amit.dto.Location;
import com.learning.amit.repository.AuditDao;

@ExtendWith(MockitoExtension.class)
class AuditServiceImpTest {

	@Mock
	private AuditDao auditDao;
	
	@InjectMocks
	private AuditServiceImp auditService;
	
	@Test
	void testAudit() throws IOException {
		
		int[] value = null;
		when(auditDao.saveList(Mockito.anyList())).thenReturn(value);
		Map<String, Boolean> response = auditService.auditSaveLog(getMockData());
		assertNotNull(response);
		
	}
	
	private static Location getMockData() {
		Location location=new Location();
		String path="C:\\Users\\Amit_Kumar100\\Downloads\\smallProject\\amit\\src\\test\\resources\\logFile.txt";
		location.setPath(path);
		return location;
	}

}
