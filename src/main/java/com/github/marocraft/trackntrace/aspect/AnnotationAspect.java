package com.github.marocraft.trackntrace.aspect;

import java.io.IOException;

import javax.annotation.PostConstruct;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import com.github.marocraft.trackntrace.collect.LogCollector;
import com.github.marocraft.trackntrace.config.TnTConfiguration;
import com.github.marocraft.trackntrace.domain.LogLevel;
import com.github.marocraft.trackntrace.domain.LogTrace;
import com.github.marocraft.trackntrace.generate.LogBuilder;
import com.github.marocraft.trackntrace.publish.ILogPublisher;
import com.github.marocraft.trackntrace.publish.LoggerThread;
import com.github.marocraft.trackntrace.publish.ThreadPoolManager;

/**
 * Annotations Core processing aspect allowing to run the behavior of the
 * framework defined annotations like @Trace
 * 
 * @author Houseine TASSA
 * @author Sallah KOKAINA
 * @since 0.0.3
 * 
 */
@Aspect
@Component
@Scope("singleton")
@PropertySource("classpath:/application.properties")
public class AnnotationAspect {

	@Autowired
	LogBuilder logBuilder;

	@Autowired
	TnTConfiguration config;

	@Autowired
	ILogPublisher<String> logPublisher;

	@Value("${tnt.multithread.poolsize:1}")
	Integer threadPoolSize;

	@Autowired
	ThreadPoolManager threadPoolManager;

	@Autowired
	ApplicationContext applicationContext;

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
		LogCollector collector = new LogCollector();
		LogLevel logLevel = collector.getLevel(joinPoint);
		String logMessage = collector.getMessage(joinPoint);
		Signature methodSignature = joinPoint.getSignature();
		Object clazz = joinPoint.getTarget();

		LogTrace logTrace = collector.collect(clazz.getClass().getName(), methodSignature.getName(), logLevel,
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

	@PostConstruct
	public void postConstruct() {

		// start multi-threading
		threadPoolManager.initialize();
		for (int i = 1; i <= threadPoolSize; i++) {
			threadPoolManager.submitThread(applicationContext.getBean(LoggerThread.class));
		}
	}
}