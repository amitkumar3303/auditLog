package com.learning.amit.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.learning.amit.controller.service.AuditService;



@AutoConfigureMockMvc
@WebMvcTest
@ContextConfiguration(classes = {AuditController.class})
class AuditControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	 private AuditService auditService;
	
	@InjectMocks
	private AuditController auditController;
	@Test
	void testGetAudit() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post("/audit/auditvalidationlog")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"path\":\"C:\\\\Users\\\\Amit_Kumar100\\\\Downloads\\\\smallProject\\\\logFile.txt\"}"))
		.andExpect(status().isOk())
		.andReturn();
	}

}
