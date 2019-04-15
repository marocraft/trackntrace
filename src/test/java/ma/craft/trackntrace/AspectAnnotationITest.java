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
import ma.craft.trackntrace.domain.Template;
import ma.craft.trackntrace.generate.LogBuilder;
import ma.craft.trackntrace.publish.LogPublisher;
import ma.craft.trackntrace.publish.LoggerThread;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringAOPContext.class)
public class AspectAnnotationITest {

	@Autowired
	TestService testService;
	@Autowired
	Template template;

	@Autowired
	LogPublisher logPublisher;

	@Autowired
	LogBuilder logBuilder;
	
	LoggerThread loggerThread;

	@Test
	public void shoulTakeMessage() throws IOException, InterruptedException, FileNotFoundException {
		loggerThread= new LoggerThread();
		logPublisher.publish("my log");
		Thread.sleep(1000);
		Assert.assertEquals(1, logPublisher.size());
		logPublisher.clear();
		Assert.assertEquals(0, logPublisher.size());
	}
}
