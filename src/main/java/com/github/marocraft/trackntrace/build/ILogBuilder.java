package com.github.marocraft.trackntrace.build;

import org.springframework.stereotype.Component;

import com.github.marocraft.trackntrace.domain.LogTrace;

/**
 * Interface of log building from a from LogTrace objects
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