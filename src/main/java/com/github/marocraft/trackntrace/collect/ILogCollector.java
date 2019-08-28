package com.github.marocraft.trackntrace.collect;

import java.lang.reflect.Method;

import org.aspectj.lang.reflect.MethodSignature;

import com.github.marocraft.trackntrace.annotation.Trace;
import com.github.marocraft.trackntrace.domain.LogLevel;
import com.github.marocraft.trackntrace.domain.LogTrace;
import com.github.marocraft.trackntrace.logger.LogCollection;

/**
 * Interface for Collecting informations to log
 * 
 * @author: Khalid ELLABBADI
 */
public interface ILogCollector {

	/**
	 * Collect informations to log
	 * 
	 * @param logCollection contains information that will be logged.
	 * @return Logtrace is a trace of logs.
	 */
	public LogTrace collect(LogCollection logCollection);

	/**
	 * Return Log collector level
	 * 
	 * @param signature is the method signature that is annotated with Trace Annotation
	 * @return the log level 
	 */
	default LogLevel getLevelFromSignature(MethodSignature signature) {
		Method method = signature.getMethod();
		Trace myAnnotation = method.getAnnotation(Trace.class);
		return myAnnotation.level();
	}

	/**
	 * Return log message
	 * 
	 * @param signature is the method signature that is annotated with Trace Annotation
	 * @return the message from the annotation Trace parameters
	 */
	default String getMessageFromSignature(MethodSignature signature) {
		Method method = signature.getMethod();
		Trace myAnnotation = method.getAnnotation(Trace.class);
		return myAnnotation.message();
	}

}