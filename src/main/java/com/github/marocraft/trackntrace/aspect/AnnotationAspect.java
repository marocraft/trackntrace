package com.github.marocraft.trackntrace.aspect;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.time.LocalDateTime;

import javax.annotation.PostConstruct;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import com.github.marocraft.trackntrace.build.ILogBuilder;
import com.github.marocraft.trackntrace.collect.ILogCollector;
import com.github.marocraft.trackntrace.config.IConfigurationTnT;
import com.github.marocraft.trackntrace.domain.ILogTrace;
import com.github.marocraft.trackntrace.domain.LogLevel;
import com.github.marocraft.trackntrace.http.HttpLog;
import com.github.marocraft.trackntrace.http.ICorrelater;
import com.github.marocraft.trackntrace.publish.ILogPublisher;
import com.github.marocraft.trackntrace.publish.LoggerThread;
import com.github.marocraft.trackntrace.publish.ThreadPoolManager;

/**
 * Annotations Core processing aspect allowing to run the behavior of the
 * framework defined annotations like @Trace
 * 
 * @author Houseine TASSA
 * @author Sallah KOKAINA
 * @author Khalid ELABBADI
 * @since 0.0.3
 * 
 */
@Aspect
@Component
public class AnnotationAspect {

	@Autowired
	@Qualifier("configurationTnTDefault")
	IConfigurationTnT config;

	@Autowired
	ILogBuilder logBuilder;

	@Autowired
	ILogPublisher<String> logPublisher;

	@Autowired
	@Qualifier("defaultLogCollector")
	ILogCollector logCollector;

	@Autowired
	ThreadPoolManager threadPoolManager;

	@Autowired
	ApplicationContext applicationContext;

	@Autowired
	ICorrelater correlator;

	@Autowired
	@Qualifier("logTraceDefault")
	ILogTrace logtraceDefault;

	@Autowired
	@Qualifier("logTraceRest")
	ILogTrace logtraceRest;

	@Autowired
	HttpLog httpverb;
	
	@Autowired
	@Qualifier("restLogger")
	private Logger restLogger;
	
	@Autowired
	@Qualifier("defaultLogger")
	private Logger defaultLogger;
	
	

	/**
	 * Start multi-threading
	 * 
	 */
	@PostConstruct
	public void postConstruct() {
		threadPoolManager.initialize();
		for (int i = 1; i <= config.getThreadPoolsize(); i++) {
			threadPoolManager.addNewThread(applicationContext.getBean(LoggerThread.class));
		}
	}

	/**
	 * Collect data about annotated methods execution and generate a specific based
	 * template
	 * 
	 * @param joinPoint
	 * @throws Throwable
	 */
	@Around(value = "@annotation(com.github.marocraft.trackntrace.annotation.Trace)")
	public Object whenAnnotatedWithTrace(final ProceedingJoinPoint joinPoint) throws Throwable {
		StopWatch stopWatch = startTimer();
		Object proceed = executeAnnotedMethod(joinPoint);
		stopTimer(stopWatch);
		generateLog(joinPoint, stopWatch);
		return proceed;
	}

	/**
	 * Collect and generate logs
	 * 
	 * @param joinPoint
	 * @param stopWatch
	 * @throws IllegalAccessException
	 * @throws IOException
	 */
	private void generateLog(final JoinPoint joinPoint, StopWatch stopWatch) throws IllegalAccessException {
		MethodSignature signature = (MethodSignature) joinPoint.getSignature();
		LogLevel logLevel = logCollector.getLevel(signature);
		String logMessage = logCollector.getMessage(signature);
		Object clazz = joinPoint.getTarget();
		
		LogCollection logCollection = new LogCollection(
				clazz.getClass().getName(), signature.getName(), logLevel,
				stopWatch, logMessage, correlator, LocalDateTime.now(), httpverb);

		LogResolver resolver = new LogResolver(getLogStrategy(signature));
		resolver.process(logCollection);
	}

	private Object executeAnnotedMethod(final ProceedingJoinPoint joinPoint) throws Throwable {
		return joinPoint.proceed();
	}

	private void stopTimer(StopWatch stopWatch) {
		stopWatch.stop();
	}

	private StopWatch startTimer() {
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();
		return stopWatch;
	}
	
	private Logger getLogStrategy(MethodSignature joinpointSignature) {
		if(isRestAnnotated(joinpointSignature)) {
			return restLogger;
		} else {
			return defaultLogger;
		}
	}
	
	private boolean isRestAnnotated(MethodSignature signature) {
		Method method = signature.getMethod();
		Class<?> clazz= method.getDeclaringClass();
		for (Annotation annotation : clazz.getAnnotations()) {
			if ("org.springframework.web.bind.annotation.RestController".equals(annotation.annotationType().getName())) {
				return true;
			}
		}
		return false;
	}
}