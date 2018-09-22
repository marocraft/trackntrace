package ma.craft.trackntrace.collect;

import ma.craft.trackntrace.annotation.BusinessLog;
import ma.craft.trackntrace.domain.LogLevel;
import ma.craft.trackntrace.domain.LogTrace;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.reflect.MethodSignature;

import javax.annotation.Nonnull;
import java.lang.reflect.Method;

public class LogCollector {

	public LogTrace collect(String className, String methodName, @Nonnull LogLevel logLevel, long executionTime) {
		LogTrace trace = new LogTrace();
		trace.setClassName(className);
		trace.setMethodName(methodName);
		trace.setLogLevel(logLevel.name());
		trace.setExecutionTime(executionTime);
		return trace;
	}

	public LogLevel collectLogLevel(JoinPoint joinPoint) {
		MethodSignature signature = (MethodSignature) joinPoint.getSignature();
		Method method = signature.getMethod();
		BusinessLog myAnnotation = method.getAnnotation(BusinessLog.class);
		return myAnnotation.level();
	}
}
