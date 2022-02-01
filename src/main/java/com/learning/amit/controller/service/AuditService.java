package com.learning.amit.controller.service;

import java.io.IOException;
import java.util.Map;

import com.learning.amit.dto.Location;


public interface AuditService {

	public Map<String, Boolean> auditSaveLog(Location location) throws IOException;
}
