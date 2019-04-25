package com.github.marocraft.trackntrace.publish;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.github.marocraft.trackntrace.context.SpringBasicContext;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { SpringBasicContext.class })
public class LogPublisherTest {

	@Autowired
	ILogPublisher<String> logPublisher;

	@Autowired
	ThreadPoolManager threadPoolManager;

	@Test
	public void shouldTakeMessage() throws InterruptedException {
		logPublisher.publish("my log");
		Assert.assertEquals(1, logPublisher.size());
		logPublisher.get();
		Assert.assertEquals(0, logPublisher.size());
	}
}