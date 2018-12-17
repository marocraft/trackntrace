package ma.craft.trackntrace;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import ma.craft.trackntrace.aspect.AnnotationAspect;
import ma.craft.trackntrace.collect.LogCollector;
import ma.craft.trackntrace.context.SpringAOPContext;
import ma.craft.trackntrace.domain.LogLevel;
import ma.craft.trackntrace.domain.LogTrace;
import ma.craft.trackntrace.domain.Template;
import ma.craft.trackntrace.generate.LogBuilder;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringAOPContext.class)
public class LogCreationTest {

	@Autowired
	Template template;

	@Autowired
	LogBuilder logBuilder;

	@Test
	public void test() {
		AnnotationAspect exempleAspect = new AnnotationAspect();
		assertNotNull(exempleAspect);
	}

	@Test
	public void shouldCreateLogTraceClass() {
		LogCollector collector = new LogCollector();
		LogTrace logTrace = collector.collect(null, null, LogLevel.TRIVIAL, 0, "log");
		Assert.assertNotNull(logTrace);
	}

	@Test
	public void shouldCreateLogTraceClassWithData() {
		LogCollector collector = new LogCollector();
		LogTrace logTrace = collector.collect("controller", "myMethod", LogLevel.TRIVIAL, 20L, "");
		Assert.assertNotNull(logTrace.getClazz());
	}

	@Test
	public void shouldLogHaveCorrectFormat() throws IllegalAccessException {
		template.getFormat();
		LogCollector collector = new LogCollector();
		LogTrace logTrace = collector.collect("controller", "myMethod", LogLevel.TRIVIAL, 20L, "my message");

		String log = logBuilder.build(logTrace);
		assertEquals(
				"\"{methodName: myMethod,className: controller,logLevel: TRIVIAL,executionTime: 20 ms,logMessage: my message}\"",
				log);
	}

}
