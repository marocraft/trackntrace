package ma.craft.trackntrace.aspect;

import java.io.IOException;

import javax.annotation.PostConstruct;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import ma.craft.trackntrace.collect.LogCollector;
import ma.craft.trackntrace.domain.LogLevel;
import ma.craft.trackntrace.domain.LogTrace;
import ma.craft.trackntrace.domain.Template;
import ma.craft.trackntrace.generate.LogBuilder;
import ma.craft.trackntrace.publish.ILogPublisher;
import ma.craft.trackntrace.publish.LoggerThread;

/**
 *  AnnotationAspect permet de definir le traitement des différentes annotation :
 * -Trace -TechnicalLog -Restlog
 *
 */
@Aspect
@Component
@DependsOn("loggerThread")
public class AnnotationAspect {

	@Autowired
	LogBuilder logBuilder;

	@Autowired
	Template template;

	@Autowired
	ILogPublisher logPublish;

	@Autowired
	LoggerThread loggerThread;

	/**
	 * Trace aspect collecte les donées des methodes annotées et génère un log basé
	 * sur une template spécifique
	 * 
	 * @param joinPoint
	 * @throws Throwable
	 */

	@Around(value = "@annotation(ma.craft.trackntrace.annotation.Trace)")
	public Object whenAnnotatedWithTrace(final ProceedingJoinPoint joinPoint) throws Throwable {
		StopWatch stopWatch = startTimer();
		Object proceed = executeAnnotedMethod(joinPoint);
		stopTimer(stopWatch);
		collectAndGenerateLog(joinPoint, stopWatch);
		return proceed;
	}

	/**
	 * @param joinPoint
	 * @param stopWatch
	 * @throws IllegalAccessException
	 * @throws IOException
	 */
	private void collectAndGenerateLog(final JoinPoint joinPoint, StopWatch stopWatch)
			throws IllegalAccessException, IOException {
		LogCollector collector = new LogCollector();
		LogLevel LogLevel = collector.collectLogLevel(joinPoint);
		String logMessage = collector.logMessage(joinPoint);
		Signature methodSignature = joinPoint.getSignature();
		Object clazz = joinPoint.getTarget();
		LogTrace logTrace = collector.collect(clazz.getClass().getName(), methodSignature.getName(), LogLevel,
				stopWatch.getTotalTimeMillis(), logMessage);
		String log = logBuilder.build(logTrace);
		logPublish.publish(log);
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
		startLoggerThread();
	}
	
	private void startLoggerThread() {
		if (loggerThread != null) {
			loggerThread.start();
		}
	}
}