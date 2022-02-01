package com.learning.amit.dto;

import java.math.BigInteger;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class LogEvent {
	private String id;
	private String state;
	private String type;
	private String host;
	private BigInteger timestamp;	
}
