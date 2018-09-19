package com.example.demo.entities;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Aspect
@Component
public class AnnotationAspect {

	private final Log log = LogFactory.getLog(this.getClass());

	
	/**
	 * Business Log aspect
	 * Collect data about annotated methods
	 * Generate a log message based on a specific template
	 * 
	 * @param joinPoint
	 * @param businessLog
	 * @throws Throwable
	 */
	@Around(value = "@annotation(BusinessLog)")
	public void BusinessLog(final ProceedingJoinPoint joinPoint, BusinessLog businessLog) throws Throwable {
		StopWatch stopWatch = startTimer();
		executeAnnotedMethod(joinPoint);
		collectAndGenerateLog(joinPoint, stopWatch);
		stopTimer(stopWatch);
	}
	
	
	
	/**
	 * Rest Log aspect
	 * Collect data about annotated methods having also a @Path annotation
	 * Generate a log message containing URI call information based on a specific template
	 * 
	 * @param joinPoint
	 * @param businessLog
	 * @throws Throwable
	 */
	@Around(value = "@annotation(RestLog)")
	public void RestLog(final ProceedingJoinPoint joinPoint, RestLog restLog) throws Throwable {
		//0. check if method is annotated with @Path

		StopWatch stopWatch = startTimer();
		executeAnnotedMethod(joinPoint);
		stopTimer(stopWatch);
		
		//1.collect rest data from @Path annotation and http dispatcher
		// non blocking
		
		
		collectAndGenerateLog(joinPoint, stopWatch);
	}

	private void collectAndGenerateLog(final ProceedingJoinPoint joinPoint, StopWatch stopWatch) {
		String logMessage = LogBuilder.build(joinPoint.getTarget().getClass().getName(),
				joinPoint.getSignature().getName(),
				joinPoint.getArgs(), stopWatch.getTotalTimeMillis());
		log.info(logMessage);
	}

	private void executeAnnotedMethod(final ProceedingJoinPoint joinPoint) throws Throwable {
		joinPoint.proceed();
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
