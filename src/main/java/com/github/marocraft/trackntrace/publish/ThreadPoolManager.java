package com.github.marocraft.trackntrace.publish;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.marocraft.trackntrace.config.IConfigurationTnT;

/**
 * Logging running threads management and orchestration.
 * 
 * @author Houseine TASSA
 * @author Khalid ELABBADI
 *
 */
@Component
public class ThreadPoolManager {

	@Autowired
	IConfigurationTnT config;

	ExecutorService executorService;

	public void initialize() {
		executorService = Executors.newFixedThreadPool(config.getThreadPoolsize());
	}

	public void addNewThread(Runnable thread) {
		executorService.submit(thread);
	}

	public void shutdown() {
		executorService.shutdown();
	}
}