package com.learning.amit.repository;

import java.util.List;

import com.learning.amit.dto.LogEvent;


public interface AuditDao {

	 int[]  saveList(List<LogEvent> logevent);
   
}
