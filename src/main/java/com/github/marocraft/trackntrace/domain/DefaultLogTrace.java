package com.github.marocraft.trackntrace.domain;

import org.springframework.stereotype.Component;

import com.github.marocraft.trackntrace.annotation.Mapping;

/**
 * LogTrace transfer object. Holds traced item information
 * 
 * @author Houseine TASSA
 * @author Sallah KOKAINA
 *
 */
@Component("defaultLogTrace")
public class DefaultLogTrace extends LogTrace {

	public DefaultLogTrace() {
		super();
	}

	public DefaultLogTrace(long time, String method, String clazz, String level, String code, String message,
			String timeStamps, String traceId, String spanId, String parentId, String ip) {
		super();
		this.time = time;
		this.method = method;
		this.clazz = clazz;
		this.level = level;
		this.code = code;
		this.message = message;
		this.timeStamps = timeStamps;
		this.traceId = traceId;
		this.spanId = spanId;
		this.parentId = parentId;
		this.ip = ip;
	}

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

	@Mapping(field = "timeStamps")
	private String timeStamps;

	@Mapping(field = "traceId")
	private String traceId;

	@Mapping(field = "spanId")
	private String spanId;

	@Mapping(field = "parentId")
	private String parentId;

	@Mapping(field = "ip")
	private String ip;

	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getClazz() {
		return clazz;
	}

	public void setClazz(String clazz) {
		this.clazz = clazz;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getTimeStamps() {
		return timeStamps;
	}

	public void setTimeStamps(String timeStamps) {
		this.timeStamps = timeStamps;
	}

	public String getTraceId() {
		return traceId;
	}

	public void setTraceId(String traceId) {
		this.traceId = traceId;
	}

	public String getSpanId() {
		return spanId;
	}

	public void setSpanId(String spanId) {
		this.spanId = spanId;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

}