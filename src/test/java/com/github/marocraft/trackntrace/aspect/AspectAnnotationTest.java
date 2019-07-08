package com.github.marocraft.trackntrace.aspect;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.github.marocraft.trackntrace.build.ILogBuilder;
import com.github.marocraft.trackntrace.config.IConfigurationTnT;
import com.github.marocraft.trackntrace.context.SpringAOPContext;
import com.github.marocraft.trackntrace.domain.DefaultLogTrace;
import com.github.marocraft.trackntrace.publish.ILogPublisher;
import com.github.marocraft.trackntrace.publish.ThreadPoolManager;
import com.github.marocraft.trackntrace.utils.CommonUtils;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringAOPContext.class)
public class AspectAnnotationTest {

	@Autowired
	@Qualifier("configurationTnTDefault")
	IConfigurationTnT config;

	@Autowired
	ILogPublisher<String> logPublisher;

	@Autowired
	@Qualifier("defaultLogBuilder")
	ILogBuilder logBuilder;

	@Autowired
	ThreadPoolManager threadPoolManager;

	@Autowired
	CommonUtils commonUtils;

	@Test
	public void shouldBuildLogs()
			throws IOException, InterruptedException, FileNotFoundException, IllegalAccessException {
		DefaultLogTrace logTrace = new DefaultLogTrace(234, "sleep", "com.github.marocraft.trackntrace.TestService",
				"NORMAL", "234", "new message", "", "", "", "", "");
		String log = logBuilder.build(logTrace);
		Assert.assertEquals(
				"{\"methodName\": \"sleep\",\"className\": \"com.github.marocraft.trackntrace.TestService\",\"logLevel\": \"NORMAL\",\"executionTime\": \"234\",\"logMessage\": \"new message\",\"timeStamps\": \"\",\"traceId\": \"\",\"spanId\": \"\",\"parentId\": \"\",\"ip\": \"\"}",
				log);
	}

	public void shouldNotPublishLogs() throws IOException, InterruptedException, FileNotFoundException {
		logPublisher.clear();
		logPublisher.publish(null);
		Assert.assertEquals(0, logPublisher.size());
	}

//	@Test
//	public void shouldLog() {
//		testService.sleep(200);
//	}

}