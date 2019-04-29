package com.github.marocraft.trackntrace.http;

/**
 * Expose tracing identifiers.
 * 
 * @author Khalid ELABBADI
 * @author Houseine TASSA
 *
 */
public interface ICorrelater {

	public String getTraceId();

	public void setTraceId(String traceId);

	public String getSpanId();

	public void setSpanId(String spanId);
}