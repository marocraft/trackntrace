package com.github.marocraft.trackntrace;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.github.marocraft.trackntrace.config.IConfigurationTnT;
import com.github.marocraft.trackntrace.context.SpringAOPContext;
import com.github.marocraft.trackntrace.generate.ILogBuilder;
import com.github.marocraft.trackntrace.publish.ILogPublisher;
import com.github.marocraft.trackntrace.publish.LoggerThread;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringAOPContext.class)
public class AspectAnnotationITest {
	
	@Autowired
	ApplicationContext applicationContext;
	
	@Autowired
	IConfigurationTnT config;

	@Autowired
	ILogPublisher<String> logPublisher;

	@Autowired
	ILogBuilder logBuilder;
	
	@Autowired
	TestService testService;

	LoggerThread loggerThread;

	@Test
	public void shoulTakeMessage() throws IOException, InterruptedException, FileNotFoundException {
		loggerThread = applicationContext.getBean(LoggerThread.class);
		logPublisher.publish("my log");
		Assert.assertEquals(1, logPublisher.size());
		logPublisher.clear();
		Assert.assertEquals(0, logPublisher.size());
	}
}