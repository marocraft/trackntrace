package com.github.marocraft.trackntrace.collect;

import org.springframework.stereotype.Component;

import com.github.marocraft.trackntrace.domain.LogTrace;
import com.github.marocraft.trackntrace.domain.DefaultLogTrace;
import com.github.marocraft.trackntrace.logger.LogCollection;

@Component("defaultLogCollector")
public class DefaultLogCollector implements ILogCollector {

	public LogTrace collect(LogCollection logCollection) {
		return new DefaultLogTrace(
				logCollection.executionTime(), logCollection.getMethodSignature(), logCollection.getClassName(),
				logCollection.getLogLevel().name(), null, logCollection.getLogMessage(),
				logCollection.getTraceId(), logCollection.getSpanId(), logCollection.getCurrentTimestamp());
	}

}
