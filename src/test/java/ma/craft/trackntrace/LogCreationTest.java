package ma.craft.trackntrace;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.github.marocraft.trackntrace.aspect.AnnotationAspect;
import com.github.marocraft.trackntrace.collect.LogCollector;
import com.github.marocraft.trackntrace.config.TnTConfiguration;
import com.github.marocraft.trackntrace.domain.LogLevel;
import com.github.marocraft.trackntrace.domain.LogTrace;
import com.github.marocraft.trackntrace.generate.LogBuilder;

import ma.craft.trackntrace.context.SpringAOPContext;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringAOPContext.class)
public class LogCreationTest {

	@Autowired
	TnTConfiguration config;

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
		config.getFormat();
		LogCollector collector = new LogCollector();
		LogTrace logTrace = collector.collect("controller", "myMethod", LogLevel.TRIVIAL, 20L, "my message");

		String log = logBuilder.build(logTrace);
		assertEquals(
				"{\"methodName\": \"myMethod\",\"className\": \"controller\",\"logLevel\": \"TRIVIAL\",\"executionTime\": \"20\",\"logMessage\": \"my message\"}\"",
				log);
	}
}
