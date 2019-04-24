package com.github.marocraft.trackntrace.generate;

import org.springframework.stereotype.Component;

import com.github.marocraft.trackntrace.domain.LogTrace;

/**
 * Build a log line from a from LogTrace object
 * 
 * @author Khalid ELABBADI
 *
 */
@Component
public interface ILogBuilder {

	/**
	 * Construct logs from LogTrace object
	 * 
	 * @param logTrace
	 * @return
	 * @throws IllegalAccessException
	 */
	public String build(LogTrace logTrace) throws IllegalAccessException;
}