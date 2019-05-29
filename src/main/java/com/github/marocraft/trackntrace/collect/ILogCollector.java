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
	 * @param logCollection
	 * @return
	 */
	public LogTrace collect(LogCollection logCollection);

	/**
	 * Return Log collector level
	 * 
	 * @param signature
	 * @return
	 */
	default LogLevel getLevelFromSignature(MethodSignature signature) {
		Method method = signature.getMethod();
		Trace myAnnotation = method.getAnnotation(Trace.class);
		return myAnnotation.level();
	}

	/**
	 * Return log message
	 * 
	 * @param signature
	 * @return
	 */
	default String getMessageFromSignature(MethodSignature signature) {
		Method method = signature.getMethod();
		Trace myAnnotation = method.getAnnotation(Trace.class);
		return myAnnotation.message();
	}

}