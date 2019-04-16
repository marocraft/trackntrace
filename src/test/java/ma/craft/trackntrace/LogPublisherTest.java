package ma.craft.trackntrace;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import ma.craft.trackntrace.context.SpringBasicContext;
import ma.craft.trackntrace.publish.ILogPublisher;
import ma.craft.trackntrace.publish.LogPublisher;
import ma.craft.trackntrace.publish.LoggerThread;
import ma.craft.trackntrace.publish.ThreadPoolManager;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {SpringBasicContext.class})
public class LogPublisherTest {
	
	ILogPublisher<String> logPublisher;
	ThreadPoolManager threadPoolManager;
	@Before
	public void init() {
		 logPublisher= new LogPublisher();
		 threadPoolManager= new ThreadPoolManager();
	}
	
	@Test
	public void shouldTakeMessage() throws InterruptedException {
		logPublisher.publish("my log");
		Assert.assertEquals(1, logPublisher.size());
		logPublisher.get();
		Assert.assertEquals(0, logPublisher.size());
	}
	
}