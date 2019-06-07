package com.github.marocraft.trackntrace.collect;

import org.springframework.stereotype.Component;

import com.github.marocraft.trackntrace.domain.LogTrace;
import com.github.marocraft.trackntrace.domain.RestLogTrace;
import com.github.marocraft.trackntrace.logger.LogCollection;

@Component("restLogCollector")
public class RestLogCollector implements ILogCollector {

	public LogTrace collect(LogCollection logCollection) {
		return new RestLogTrace(logCollection.executionTime(), logCollection.getMethodSignature(),
				logCollection.getClassName(), logCollection.getLogLevel().name(), logCollection.getLogMessage(),
				logCollection.getCurrentTimestamp(), logCollection.getHttpVerb(), logCollection.getHttpStatus(),
				logCollection.getHttpURI(),logCollection.getTraceId(), logCollection.getSpanId(), logCollection.getParentId());
	}
}
