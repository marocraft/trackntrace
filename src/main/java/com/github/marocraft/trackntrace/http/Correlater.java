package com.github.marocraft.trackntrace.http;

import org.springframework.stereotype.Component;

/**
 * Expose tracing identifiers.
 * 
 * @author Khalid ELABBADI
 * @author Houseine TASSA
 *
 */
@Component
public class Correlater implements ICorrelater {

	private String traceId ;
	
	private String spanId ;

	public Correlater(String traceId, String spanId) {
		super();
		this.traceId = traceId;
		this.spanId = spanId;
	}

	public Correlater() {
		super();
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
}