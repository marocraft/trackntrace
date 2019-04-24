package com.github.marocraft.trackntrace.collect;

import javax.annotation.Nonnull;

import org.aspectj.lang.JoinPoint;

import com.github.marocraft.trackntrace.domain.LogLevel;
import com.github.marocraft.trackntrace.domain.LogTrace;

/**
 * Interface for Collecting informations to log
 * 
 * @author: Khalid ELLABBADI
 */
public interface ILogCollector {

	/**
	 * Collect informations to log
	 * 
	 * @param className
	 * @param methodName
	 * @param logLevel
	 * @param executionTime
	 * @param logMessage
	 * @return
	 */
	public LogTrace collect(String className, String methodName, @Nonnull LogLevel logLevel, long executionTime, String logMessage);

	/**
	 * Return Log collector level
	 * 
	 * @param joinPoint
	 * @return
	 */
	public LogLevel getLevel(JoinPoint joinPoint);

	/**
	 * Return log message
	 * 
	 * @param joinPoint
	 * @return
	 */
	public String getMessage(JoinPoint joinPoint);
}