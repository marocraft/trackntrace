package ma.craft.trackntrace;

import ma.craft.trackntrace.aspect.AnnotationAspect;
import ma.craft.trackntrace.collect.LogCollector;
import ma.craft.trackntrace.domain.LogLevel;
import ma.craft.trackntrace.domain.LogTrace;
import ma.craft.trackntrace.generate.LogBuilder;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import lombok.extern.slf4j.Slf4j;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;


@RunWith(JUnit4.class)
public class LogCreationTest {

	@Test
	public void test() {
		AnnotationAspect exempleAspect = new AnnotationAspect();
		assertNotNull(exempleAspect);
	}

	@Test
	public void shouldCreateLogTraceClass(){
		LogCollector collector = new LogCollector();
		LogTrace logTrace = collector.collect(null, null, LogLevel.TRIVIAL, 0);
		Assert.assertNotNull(logTrace);
	}

	@Test
	public void shouldCreateLogTraceClassWithData(){
		LogCollector collector = new LogCollector();
		LogTrace logTrace = collector.collect("controller", "myMethod", LogLevel.TRIVIAL, 20L);
		Assert.assertNotNull(logTrace.getClazz());
	}

	@Test
	public void shouldLogHaveCorrectFormat() throws IllegalAccessException {
		LogCollector collector = new LogCollector();
		LogTrace logTrace = collector.collect("controller", "myMethod", LogLevel.TRIVIAL, 20L);
		String log = LogBuilder.build(logTrace);
		assertEquals("{ methodName: myMethod, className: controller,logLevel: TRIVIAL, executionTime: 20 ms}", log);
	}
}
