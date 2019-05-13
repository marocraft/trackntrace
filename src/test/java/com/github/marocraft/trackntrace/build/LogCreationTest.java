package com.github.marocraft.trackntrace.build;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.StopWatch;

import com.github.marocraft.trackntrace.aspect.AnnotationAspect;
import com.github.marocraft.trackntrace.aspect.LogCollection;
import com.github.marocraft.trackntrace.collect.ILogCollector;
import com.github.marocraft.trackntrace.config.IConfigurationTnT;
import com.github.marocraft.trackntrace.context.SpringAOPContext;
import com.github.marocraft.trackntrace.domain.ILogTrace;
import com.github.marocraft.trackntrace.domain.LogLevel;
import com.github.marocraft.trackntrace.domain.LogTraceDefault;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringAOPContext.class)
public class LogCreationTest {

	@Autowired
	@Qualifier("configurationTnTDefault")
	IConfigurationTnT config;

	@Autowired
	ILogBuilder logBuilder;

	@Autowired
	@Qualifier("defaultLogCollector")
	ILogCollector logCollector;

	@Test
	public void test() {
		AnnotationAspect exempleAspect = new AnnotationAspect();
		assertNotNull(exempleAspect);
	}

	@Test
	public void shouldCreateLogTraceClass() {
		ILogTrace logTrace = logCollector.collect(new LogCollection(null,null,null,null,null,null,null,null));
		Assert.assertNotNull(logTrace);
	}

	@Test
	public void shouldCreateLogTraceClassWithData() {
		LogTraceDefault logTrace = (LogTraceDefault) logCollector.collect(new LogCollection("clazz",null,null,null,null,null,null,null));
		Assert.assertNotNull(logTrace.getClazz());
	}

	@Test
	public void shouldLogHaveCorrectFormat() throws IllegalAccessException {
		config.getFormat();
		LogTraceDefault logTrace = 
				(LogTraceDefault) logCollector.collect(
						new LogCollection("controller", "myMethod", LogLevel.TRIVIAL, new StopWatch(), "my message", null, null, null));

		String log = logBuilder.build(logTrace);
		assertEquals(
				"{\"methodName\": \"myMethod\",\"className\": \"controller\",\"logLevel\": \"TRIVIAL\",\"executionTime\": \"20\",\"logMessage\": \"my message\",\"traceId\": \"\",\"spanId\": \"\",\"timeStamps\": \"\"}",
				log);
	}
}