package ma.craft.trackntrace;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import ma.craft.trackntrace.context.SpringAOPContext;
import ma.craft.trackntrace.domain.LogTrace;
import ma.craft.trackntrace.domain.Template;
import ma.craft.trackntrace.generate.LogBuilder;
import ma.craft.trackntrace.publish.ILogPublisher;
import ma.craft.trackntrace.publish.LogPublisher;
import ma.craft.trackntrace.publish.LoggerThread;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringAOPContext.class)
public class AspectAnnotationTest {

	@Autowired
	TestService testService;
	@Autowired
	Template template;

	@Autowired
	ILogPublisher<String> logPublisher;

	@Autowired
	LogBuilder logBuilder;

	@Autowired
	LoggerThread loggerThread;

	@Test
	public void shouldBuildLogs()
			throws IOException, InterruptedException, FileNotFoundException, IllegalAccessException {
		LogTrace logTrace = new LogTrace(234, "sleep", "ma.craft.trackntrace.TestService", "NORMAL", "234",
				"new message");
		String log = logBuilder.build(logTrace);
		Assert.assertEquals(
				"{\"methodName\": \"sleep\",\"className\": \"ma.craft.trackntrace.TestService\",\"logLevel\": \"NORMAL\",\"executionTime\": \"234\",\"logMessage\": \"new message\"}\"",
				log);
	}

	@Test
	public void shouldPublishLogs() throws IOException, InterruptedException, FileNotFoundException {
		logPublisher.clear();
		logPublisher.publish("my log");
		Assert.assertEquals(1, logPublisher.size());
		logPublisher.clear();
		Assert.assertEquals(0, logPublisher.size());
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
