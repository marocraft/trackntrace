package com.github.marocraft.trackntrace.logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.github.marocraft.trackntrace.build.ILogBuilder;
import com.github.marocraft.trackntrace.collect.ILogCollector;
import com.github.marocraft.trackntrace.publish.ILogPublisher;

@Component("restLogger")
public class RestLogger implements Logger {

	@Autowired
	@Qualifier("restLogCollector")
	private ILogCollector logCollector;

	@Autowired
	@Qualifier("restLogBuilder")
	private ILogBuilder logBuilder;

	@Autowired
	private ILogPublisher<String> logPublisher;

	public ILogCollector logCollector() {
		return logCollector;
	}

	public ILogBuilder logBuilder() {
		return logBuilder;
	}

	public ILogPublisher<String> logPublisher() {
		return logPublisher;
	}

}
