package com.github.marocraft.trackntrace;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.github.marocraft.trackntrace.context.SpringBasicContext;
import com.github.marocraft.trackntrace.publish.ILogPublisher;
import com.github.marocraft.trackntrace.publish.LogPublisher;
import com.github.marocraft.trackntrace.publish.ThreadPoolManager;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { SpringBasicContext.class })
public class LogPublisherTest {

	ILogPublisher<String> logPublisher;
	ThreadPoolManager threadPoolManager;

	@Before
	public void init() {
		logPublisher = new LogPublisher();
		threadPoolManager = new ThreadPoolManager();
	}

	@Test
	public void shouldTakeMessage() throws InterruptedException {
		logPublisher.publish("my log");
		Assert.assertEquals(1, logPublisher.size());
		logPublisher.get();
		Assert.assertEquals(0, logPublisher.size());
	}
}