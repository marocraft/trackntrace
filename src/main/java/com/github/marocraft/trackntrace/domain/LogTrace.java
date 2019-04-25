package com.github.marocraft.trackntrace.domain;

import com.github.marocraft.trackntrace.annotation.Mapping;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * LogTrace transfer object. Holds traced item information
 * 
 * @author Houseine TASSA
 * @author Sallah KOKAINA
 *
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LogTrace {

	@Mapping(field = "executionTime")
	private long time;

	@Mapping(field = "methodName")
	private String method;

	@Mapping(field = "className")
	private String clazz;

	@Mapping(field = "logLevel")
	private String level;

	@Mapping(field = "codeName")
	private String code;

	@Mapping(field = "logMessage")
	private String message;
}