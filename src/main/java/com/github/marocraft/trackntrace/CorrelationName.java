package com.github.marocraft.trackntrace;

/**
 * Constants for used correlations ids
 * 
 * @author Khalid ELABBADI
 *
 */
public class CorrelationName {

	private CorrelationName() {

	}

	public static final String SPAN_ID = "X-B3-TraceId";
	public static final String TRACE_ID = "X-B3-SpanId";
}