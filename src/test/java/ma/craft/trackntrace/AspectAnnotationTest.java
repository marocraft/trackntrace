package ma.craft.trackntrace;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.github.marocraft.trackntrace.config.TnTConfiguration;
import com.github.marocraft.trackntrace.domain.LogTrace;
import com.github.marocraft.trackntrace.generate.LogBuilder;
import com.github.marocraft.trackntrace.publish.ILogPublisher;
import com.github.marocraft.trackntrace.publish.ThreadPoolManager;

import ma.craft.trackntrace.context.SpringAOPContext;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringAOPContext.class)
public class AspectAnnotationTest {

	@Autowired
	TestService testService;

	@Autowired
	TnTConfiguration config;

	@Autowired
	ILogPublisher<String> logPublisher;

	@Autowired
	LogBuilder logBuilder;

	@Autowired
	ThreadPoolManager threadPoolManager;

	@Test
	public void shouldBuildLogs()
			throws IOException, InterruptedException, FileNotFoundException, IllegalAccessException {
		LogTrace logTrace = new LogTrace(234, "sleep", "ma.craft.trackntrace.TestService", "NORMAL", "234",
				"new message");
		String log = logBuilder.build(logTrace);
		Assert.assertEquals("{\"methodName\": \"sleep\",\"className\": \"ma.craft.trackntrace.TestService\","
				+ "\"logLevel\": \"NORMAL\",\"executionTime\": \"234\",\"logMessage\": \"new message\"}\"", log);
	}

	public void shouldNotPublishLogs() throws IOException, InterruptedException, FileNotFoundException {
		logPublisher.clear();
		logPublisher.publish(null);
		Assert.assertEquals(0, logPublisher.size());
	}

	@Test
	public void shouldLog() {
		testService.sleep(200);
	}
}