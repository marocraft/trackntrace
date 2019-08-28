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
	 * @param logTrace is a trace of logs
	 * @return returns String of logs
	 * @throws IllegalAccessException An IllegalAccessException is thrown when an application tries to reflectively create an instance (other than an array), set or get a field, or invoke a method
	 */
	
	public String build(LogTrace logTrace) throws IllegalAccessException;
	
}