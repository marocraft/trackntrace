package com.github.marocraft.trackntrace.logger;

import com.github.marocraft.trackntrace.build.ILogBuilder;
import com.github.marocraft.trackntrace.collect.ILogCollector;
import com.github.marocraft.trackntrace.publish.ILogPublisher;

public interface Logger {

	ILogCollector logCollector();

	ILogBuilder logBuilder();

	ILogPublisher<String> logPublisher();

}
