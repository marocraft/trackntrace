package com.github.marocraft.trackntrace.publish;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * Logging running threads management and orchestration.
 * 
 * @author Houseine TASSA
 *
 */
@Component
@PropertySource("classpath:/application.properties")
public class ThreadPoolManager {

	@Value("${tnt.multithread.poolsize:1}")
	Integer threadPoolSize;

	ExecutorService executorService;

	public void initialize() {
		executorService = Executors.newFixedThreadPool(threadPoolSize);
	}

	public void submitThread(Runnable thread) {
		executorService.submit(thread);
	}

	public void shutDownThreadPool() {
		executorService.shutdown();
	}
}