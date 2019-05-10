package com.github.marocraft.trackntrace.collect;

import javax.annotation.Nonnull;

import org.aspectj.lang.JoinPoint;

import com.github.marocraft.trackntrace.domain.LogLevel;
import com.github.marocraft.trackntrace.domain.LogTraceDefault;
import com.github.marocraft.trackntrace.domain.LogTraceRest;

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
	public LogTraceDefault collect(String className, String methodName, @Nonnull LogLevel logLevel, long executionTime, String logMessage, String traceId, String spanId,String timeStamps);

	/**
	 * Return Log collector level
	 * 
	 * @param joinPoint
	 * @return
	 */
	
	public LogTraceRest collect(String className, String methodName, @Nonnull LogLevel logLevel, long executionTime, String logMessage, String traceId, String spanId,String timeStamps,String httpVerb,String httpStatus,String httpURI);

	public LogLevel getLevel(JoinPoint joinPoint);

	/**
	 * Return log message
	 * 
	 * @param joinPoint
	 * @return
	 */
	public String getMessage(JoinPoint joinPoint);
	
	/**
	 * Detect if the class is annotated with RestController
	 * @param joinPoint
	 * @return
	 */
	public boolean isRestAnnotation(JoinPoint joinPoint);
}