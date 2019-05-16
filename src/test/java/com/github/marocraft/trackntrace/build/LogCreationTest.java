package com.github.marocraft.trackntrace.build;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.StopWatch;

import com.github.marocraft.trackntrace.annotation.Trace;
import com.github.marocraft.trackntrace.aspect.AnnotationAspect;
import com.github.marocraft.trackntrace.collect.ILogCollector;
import com.github.marocraft.trackntrace.context.SpringAOPContext;
import com.github.marocraft.trackntrace.domain.ILogTrace;
import com.github.marocraft.trackntrace.domain.LogLevel;
import com.github.marocraft.trackntrace.domain.LogTraceDefault;
import com.github.marocraft.trackntrace.http.Correlater;
import com.github.marocraft.trackntrace.http.HttpLog;
import com.github.marocraft.trackntrace.logger.LogCollection;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringAOPContext.class)
//@PrepareOnlyThisForTest({Method.class})
public class LogCreationTest {

	
	@Autowired
	@Qualifier("defaultLogBuilder")
	ILogBuilder logBuilder;

	@Autowired
	@Qualifier("defaultLogCollector")
	ILogCollector logCollector;
	
	JoinPoint joinPoint=Mockito.mock(JoinPoint.class);
	MethodSignature signature=Mockito.mock(MethodSignature.class);
	Method method=PowerMockito.mock(Method.class);
	Trace trace =PowerMockito.mock(Trace.class);
	
	LogCollection collection;
	@Before
	public void beforeTest() {
		 Mockito.when(joinPoint.getSignature()).thenReturn(signature);
		 Mockito.when(signature.getName()).thenReturn("clazz");
		 Mockito.when(signature.getMethod()).thenReturn(method);
		 Mockito.when(trace.level()).thenReturn(LogLevel.CRITICAL);
		 String date = "2019/05/16-13:07:12";
		 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd-HH:mm:ss");
		 LocalDateTime dateTime = LocalDateTime.parse(date, formatter);
		 collection= new LogCollection("clazz", joinPoint.getSignature(),new StopWatch(""),new Correlater("", ""), dateTime, new HttpLog(), LogLevel.CRITICAL, "my message");
		 collection.setLogLevel(LogLevel.CRITICAL);
		 
	}
	

	@Test
	public void test() {
		AnnotationAspect exempleAspect = new AnnotationAspect();
		assertNotNull(exempleAspect);
	}

	@Test
	public void shouldCreateLogTraceClass() {
		ILogTrace logTrace = logCollector.collect(collection);
		Assert.assertNotNull(logTrace);
	}

	@Test
	public void shouldCreateLogTraceClassWithData() {
		LogTraceDefault logTrace = (LogTraceDefault) logCollector.collect(collection);
		Assert.assertNotNull(logTrace.getClazz());
	}

	@Test
	public void shouldLogHaveCorrectFormat() throws IllegalAccessException {
		
		LogTraceDefault logTrace = 
				(LogTraceDefault) logCollector.collect(
						collection);

		String log = logBuilder.build(logTrace);
		assertEquals(
				"{\"methodName\": \"clazz\",\"className\": \"clazz\",\"logLevel\": \"CRITICAL\",\"executionTime\": \"0\",\"logMessage\": \"my message\",\"traceId\": \"\",\"spanId\": \"\",\"timeStamps\": \"2019/05/16-13:07:12\"}",
				log);
	}
}