package ma.craft.trackntrace.collect;

import ma.craft.trackntrace.annotation.Trace;
import ma.craft.trackntrace.domain.LogLevel;
import ma.craft.trackntrace.domain.LogTrace;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.reflect.MethodSignature;

import javax.annotation.Nonnull;
import java.lang.reflect.Method;

/** collecteur des informations à logger
 *  
 *Auteur: Tassa Housseine
 */
public class LogCollector {

	public LogTrace collect(String className, String methodName, @Nonnull LogLevel logLevel, long executionTime,String logMessage) {
		LogTrace trace = new LogTrace();
		trace.setClazz(className);
		trace.setMethod(methodName);
		trace.setLevel(logLevel.name());
		trace.setTime(executionTime);
		trace.setMessage(logMessage);
		return trace;
	}

	/**
	 * @param joinPoint
	 * @return
	 */
	public LogLevel collectLogLevel(JoinPoint joinPoint) {
		MethodSignature signature = (MethodSignature) joinPoint.getSignature();
		Method method = signature.getMethod();
		Trace myAnnotation = method.getAnnotation(Trace.class);
		return myAnnotation.level();
	}
	
	/**
	 * collecteur de message de log
	 * @param joinPoint
	 * @return
	 */
	public String logMessage(JoinPoint joinPoint) {
		MethodSignature signature = (MethodSignature) joinPoint.getSignature();
		Method method = signature.getMethod();
		Trace myAnnotation = method.getAnnotation(Trace.class);
		return myAnnotation.message();
		
	}
	
	
}
