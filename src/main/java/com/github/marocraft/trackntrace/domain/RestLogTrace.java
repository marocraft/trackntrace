package com.github.marocraft.trackntrace.domain;

import org.springframework.stereotype.Component;

import com.github.marocraft.trackntrace.annotation.Mapping;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * LogTrace transfer object. Holds traced item information
 * 
 * @author Tassa Housseine
 *
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Component("restLogTrace")
public class RestLogTrace extends LogTrace {

	@Mapping(field = "executionTime")
	private long time;

	@Mapping(field = "methodName")
	private String method;

	@Mapping(field = "className")
	private String clazz;

	@Mapping(field = "logLevel")
	private String level;

	@Mapping(field = "logMessage")
	private String message;

	@Mapping(field = "traceId")
	private String traceId;

	@Mapping(field = "spanId")
	private String spanId;

	@Mapping(field = "timeStamps")
	private String timeStamps;

	@Mapping(field = "httpVerb")
	private String httpVerb;

	@Mapping(field = "httpStatus")
	private String httpStatus;
	
	@Mapping(field = "httpURI")
	private String httpURI;

}
