package com.github.marocraft.trackntrace.aspect;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.github.marocraft.trackntrace.build.ILogBuilder;
import com.github.marocraft.trackntrace.collect.ILogCollector;
import com.github.marocraft.trackntrace.publish.ILogPublisher;

@Component("defaultLogger")
public class DefaultLogger implements Logger {
	
	@Autowired
	@Qualifier("defaultLogCollector")
	private ILogCollector logCollector;
	
	@Autowired
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
