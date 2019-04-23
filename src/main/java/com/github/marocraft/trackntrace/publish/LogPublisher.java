package com.github.marocraft.trackntrace.publish;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

/**
 * Publish collected logs into a blocking queue to be processed by running
 * threads
 * 
 * @author Housseine TASSA
 */
@Getter
@Component("logPublisher")
@Slf4j
public class LogPublisher implements ILogPublisher<String> {

	private BlockingQueue<String> logQueue = new LinkedBlockingDeque<>(50);

	@Override
	public void publish(String message) {
		try {
			logQueue.put(message);
		} catch (Exception ex) {
			log.error(message, ex);
		}
	}

	@Override
	public String get() throws InterruptedException {
		return logQueue.take();
	}

	public void clear() {
		logQueue.clear();
	}

	public int size() {
		return logQueue.size();
	}
}