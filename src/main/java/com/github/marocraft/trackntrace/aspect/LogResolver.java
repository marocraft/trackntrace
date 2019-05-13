package com.github.marocraft.trackntrace.aspect;

import com.github.marocraft.trackntrace.domain.ILogTrace;

public class LogResolver {
	private Logger logger;
	
	public LogResolver(Logger logger) {
		this.logger = logger;
	}

	void process(LogCollection logCollection) throws IllegalAccessException {
		ILogTrace logTrace = logger.logCollector().collect(logCollection);
		String logMessage = logger.logBuilder().build(logTrace);
		logger.logPublisher().publish(logMessage);
	}
}
