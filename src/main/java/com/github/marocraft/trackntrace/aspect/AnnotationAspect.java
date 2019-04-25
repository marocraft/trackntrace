package com.github.marocraft.trackntrace.aspect;

import java.io.IOException;

import javax.annotation.PostConstruct;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import com.github.marocraft.trackntrace.build.ILogBuilder;
import com.github.marocraft.trackntrace.collect.ILogCollector;
import com.github.marocraft.trackntrace.config.IConfigurationTnT;
import com.github.marocraft.trackntrace.domain.LogLevel;
import com.github.marocraft.trackntrace.domain.LogTrace;
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
	IConfigurationTnT config;

	@Autowired
	ILogBuilder logBuilder;

	@Autowired
	ILogPublisher<String> logPublisher;

	@Autowired
	ILogCollector logCollector;

	@Autowired
	ThreadPoolManager threadPoolManager;

	@Autowired
	ApplicationContext applicationContext;
	
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
		LogLevel logLevel = logCollector.getLevel(joinPoint);
		String logMessage = logCollector.getMessage(joinPoint);
		Signature methodSignature = joinPoint.getSignature();
		Object clazz = joinPoint.getTarget();

		LogTrace logTrace = logCollector.collect(clazz.getClass().getName(), methodSignature.getName(), logLevel,
				stopWatch.getTotalTimeMillis(), logMessage);
		String log = logBuilder.build(logTrace);

		logPublisher.publish(log);
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
}