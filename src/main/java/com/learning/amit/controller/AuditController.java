/**
 * 
 */
package com.learning.amit.controller;

import java.io.IOException;
import java.util.Map;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learning.amit.controller.service.AuditService;
import com.learning.amit.dto.Location;

import ch.qos.logback.classic.Logger;

/**
 * @author Amit_Kumar
 *
 */

@RestController
@RequestMapping("/audit")
public class AuditController {
	
	 private static final Logger logger = (Logger) LoggerFactory.getLogger(AuditController.class);
	
	 @Autowired
	 private AuditService auditService;
	
	
	 @PostMapping(value = "/auditvalidationlog")
	 public Map<String ,Boolean> auditvalidationlog(@RequestBody Location location) throws IOException{
		 logger.info("AuditController started");
			return auditService.auditSaveLog(location);
	 }
}
