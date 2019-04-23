package com.github.marocraft.trackntrace.collect;

import java.lang.reflect.Method;

import javax.annotation.Nonnull;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.reflect.MethodSignature;

import com.github.marocraft.trackntrace.annotation.Trace;
import com.github.marocraft.trackntrace.domain.LogLevel;
import com.github.marocraft.trackntrace.domain.LogTrace;

/**
 * Collect informations to log
 * 
 * @author: Housseine TASSA
 */
public class LogCollector {

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
	public LogTrace collect(String className, String methodName, @Nonnull LogLevel logLevel, long executionTime, String logMessage) {
		LogTrace tracer = new LogTrace();
		
		tracer.setClazz(className);
		tracer.setMethod(methodName);
		tracer.setLevel(logLevel.name());
		tracer.setTime(executionTime);
		tracer.setMessage(logMessage);
		
		return tracer;
	}

	/**
	 * Return Log collector level
	 * 
	 * @param joinPoint
	 * @return
	 */
	public LogLevel getLevel(JoinPoint joinPoint) {
		MethodSignature signature = (MethodSignature) joinPoint.getSignature();
		Method method = signature.getMethod();
		Trace myAnnotation = method.getAnnotation(Trace.class);
		
		return myAnnotation.level();
	}

	/**
	 * Return log message
	 * 
	 * @param joinPoint
	 * @return
	 */
	public String getMessage(JoinPoint joinPoint) {
		MethodSignature signature = (MethodSignature) joinPoint.getSignature();
		Method method = signature.getMethod();
		Trace myAnnotation = method.getAnnotation(Trace.class);
		
		return myAnnotation.message();
	}
}