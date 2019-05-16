package com.github.marocraft.trackntrace.collect;

import org.springframework.stereotype.Component;

import com.github.marocraft.trackntrace.domain.ILogTrace;
import com.github.marocraft.trackntrace.domain.LogTraceDefault;
import com.github.marocraft.trackntrace.logger.LogCollection;

@Component("defaultLogCollector")
public class DefaultLogCollector implements ILogCollector {

	public ILogTrace collect(LogCollection logCollection) {
		return new LogTraceDefault(
				logCollection.executionTime(), logCollection.getMethodSignature(), logCollection.getClassName(),
				logCollection.getLogLevel().name(), null, logCollection.getLogMessage(),
				logCollection.getTraceId(), logCollection.getSpanId(), logCollection.getCurrentTimestamp());
	}

}
