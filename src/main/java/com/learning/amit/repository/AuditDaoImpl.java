package com.learning.amit.repository;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.stereotype.Repository;

import com.learning.amit.dto.LogEvent;

@Repository
public class AuditDaoImpl implements AuditDao {
	private static final Logger LOGGER = LoggerFactory.getLogger(AuditDaoImpl.class);
	
	@Value("${sql.saveall}")
	private String saveallSql;
	private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	public AuditDaoImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
	}
	

	@Override
	public int[] saveList(List<LogEvent> logevent) {
		LOGGER.info("Repository Layer");
		SqlParameterSource[] parameters=SqlParameterSourceUtils.createBatch(logevent.toArray());
		return namedParameterJdbcTemplate.batchUpdate(saveallSql, parameters);
	 
	}
	
}
