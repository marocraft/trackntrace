package com.github.marocraft.trackntrace.logger;

import com.github.marocraft.trackntrace.domain.LogTrace;

public class LogResolver {
	private Logger logger;
	
	public LogResolver(Logger logger) {
		this.logger = logger;
	}

	public void process(LogCollection logCollection) throws IllegalAccessException {
		LogTrace logTrace = logger.logCollector().collect(logCollection);
		String logMessage = logger.logBuilder().build(logTrace);
		logger.logPublisher().publish(logMessage,logCollection.getLogLevel().name());
	}
}
