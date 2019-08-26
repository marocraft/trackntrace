package com.github.marocraft.trackntrace.logger;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import org.aspectj.lang.Signature;
import org.springframework.util.StopWatch;

import com.github.marocraft.trackntrace.domain.LogLevel;
import com.github.marocraft.trackntrace.http.HttpLog;

public class LogCollection {

	private String className;
	private String methodSignature;
	private Signature signature;
	private LogLevel logLevel;
	private StopWatch stopWatch;
	private String logMessage;
	private LocalDateTime localTime;
	private HttpLog httpLog;
	private String traceId;
	private String spanId;
	private String parentId;

	public LogCollection(String className, Signature signature, StopWatch stopWatch, LocalDateTime localTime,
			HttpLog httpLog, LogLevel logLevel, String logMessage, String traceId, String spanId, String parentId) {
		this.className = className;
		this.methodSignature = signature.getName();
		this.logLevel = logLevel;
		this.logMessage = logMessage;
		this.stopWatch = stopWatch;
		this.localTime = localTime;
		this.httpLog = httpLog;
		this.traceId = traceId;
		this.spanId = spanId;
		this.parentId = parentId;
	}

	public String getClassName() {
		return className;
	}

	public String getMethodSignature() {
		return methodSignature;
	}

	public LogLevel getLogLevel() {
		return logLevel;
	}

	public long executionTime() {
		return stopWatch.getTotalTimeMillis();
	}

	public String getLogMessage() {
		return logMessage;
	}

	public String getCurrentTimestamp() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.nnnnnnnnn X");
		ZonedDateTime zonedDateTime = ZonedDateTime.of(localTime, ZoneOffset.systemDefault().getRules().getOffset(Instant.now()));
		
		return zonedDateTime.format(dtf);
	}

	public String getHttpVerb() {
		return httpLog.getHttpVerb();
	}

	public String getHttpStatus() {
		return httpLog.getHttpStatus();
	}

	public String getHttpURI() {
		return httpLog.getHttpURI();
	}

	public Signature getSignature() {
		return signature;
	}

	public void setSignature(Signature signature) {
		this.signature = signature;
	}

	public void setLogLevel(LogLevel logLevel) {
		this.logLevel = logLevel;
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

	public String getPublicIpAddress() {
		return httpLog.getIp();

	}

}