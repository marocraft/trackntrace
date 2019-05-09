package com.github.marocraft.trackntrace.build;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.github.marocraft.trackntrace.domain.ILogTrace;

/**
 * Interface of log building from a from LogTrace objects
 * 
 * @author Khalid ELABBADI
 *
 */
@Component
public interface ILogBuilder {

	/**
	 * Construct logs from LogTraceDefault object
	 * 
	 * @param logTrace
	 * @return
	 * @throws IllegalAccessException
	 */
	@Qualifier("logTraceDefault")
	public String build(ILogTrace logTrace) throws IllegalAccessException;
	
	/**
	 * Construct logs from LogTraceRest object
	 * @param logTrace
	 * @return
	 * @throws IllegalAccessException
	 */
	@Qualifier("logTraceRest")
	public String buildRest(ILogTrace logTrace)throws IllegalAccessException;
}