package com.github.marocraft.trackntrace.collect;

import org.springframework.stereotype.Component;

import com.github.marocraft.trackntrace.aspect.LogCollection;
import com.github.marocraft.trackntrace.domain.ILogTrace;
import com.github.marocraft.trackntrace.domain.LogTraceRest;

@Component("restLogCollector")
public class RestLogCollector implements ILogCollector {

	public ILogTrace collect(LogCollection logCollection) {
		return new LogTraceRest(
				logCollection.executionTime(), logCollection.getMethodSignature(), logCollection.getClassName(),
				logCollection.getLogLevel().name(), logCollection.getLogMessage(),
				logCollection.getTraceId(), logCollection.getSpanId(), logCollection.getCurrentTimestamp(),
				logCollection.getHttpVerb(), logCollection.getHttpStatus(), logCollection.getHttpURI());
	}
}
