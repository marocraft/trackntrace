package com.github.marocraft.trackntrace.logger;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.aspectj.lang.Signature;
import org.springframework.util.StopWatch;

import com.github.marocraft.trackntrace.domain.LogLevel;
import com.github.marocraft.trackntrace.http.HttpLog;
import com.github.marocraft.trackntrace.http.ICorrelater;

public class LogCollection {

	private String className;
	private String methodSignature;
	private Signature signature;
	private LogLevel logLevel;
	private StopWatch stopWatch;
	private String logMessage;
	private ICorrelater correlator;
	private LocalDateTime localTime;
	private HttpLog httpLog;

	public LogCollection(String className, Signature signature, StopWatch stopWatch, ICorrelater correlator,
			LocalDateTime localTime, HttpLog httpLog, LogLevel logLevel, String logMessage) {
		this.className = className;
		this.methodSignature = signature.getName();
		this.logLevel = logLevel;
		this.logMessage = logMessage;
		this.stopWatch = stopWatch;
		this.correlator = correlator;
		this.localTime = localTime;
		this.httpLog = httpLog;
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

	public String getTraceId() {
		return correlator.getTraceId();
	}

	public String getSpanId() {
		return correlator.getSpanId();
	}

	public String getCurrentTimestamp() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd-HH:mm:ss");
		return localTime.format(dtf);
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

}
