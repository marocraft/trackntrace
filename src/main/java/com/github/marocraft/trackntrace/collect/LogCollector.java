package com.github.marocraft.trackntrace.collect;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import javax.annotation.Nonnull;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import com.github.marocraft.trackntrace.annotation.Trace;
import com.github.marocraft.trackntrace.domain.LogLevel;
import com.github.marocraft.trackntrace.domain.LogTraceDefault;
import com.github.marocraft.trackntrace.domain.LogTraceRest;

/**
 * Collect informations to log
 * 
 * @author: Housseine TASSA
 * @author Sallah KOKAINA
 */
@Component
public class LogCollector implements ILogCollector {

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
	public LogTraceDefault collect(String className, String methodName, @Nonnull LogLevel logLevel, long executionTime,
			String logMessage, String traceId, String spanId, String timeStamps) {
		LogTraceDefault tracer = new LogTraceDefault();

		tracer.setClazz(className);
		tracer.setMethod(methodName);
		tracer.setLevel(logLevel.name());
		tracer.setTime(executionTime);
		tracer.setMessage(logMessage);
		tracer.setTraceId(traceId);
		tracer.setSpanId(spanId);
		tracer.setTimeStamps(timeStamps);

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

	@Override
	public LogTraceRest collect(String className, String methodName, LogLevel logLevel, long executionTime,
			String logMessage, String traceId, String spanId, String timeStamps, String httpVerb, String httpStatus,
			String httpURI) {
		LogTraceRest tracer = new LogTraceRest();

		tracer.setClazz(className);
		tracer.setMethod(methodName);
		tracer.setLevel(logLevel.name());
		tracer.setTime(executionTime);
		tracer.setMessage(logMessage);
		tracer.setTraceId(traceId);
		tracer.setSpanId(spanId);
		tracer.setTimeStamps(timeStamps);
		tracer.setHttpVerb(httpVerb);
		tracer.setHttpStatus(httpStatus);
		tracer.setHttpURI(httpURI);
		
		return tracer;
	}

	public boolean isRestAnnotation(JoinPoint joinPoint) {
		MethodSignature signature = (MethodSignature) joinPoint.getSignature();
		Method method = signature.getMethod();
		Class<?> clazz= method.getDeclaringClass();
		Annotation[] ann = clazz.getAnnotations();
		for (Annotation annotation : ann) {
			if (annotation.annotationType().getName()=="org.springframework.web.bind.annotation.RestController")
				return true;
		}
		return false;

	}

}