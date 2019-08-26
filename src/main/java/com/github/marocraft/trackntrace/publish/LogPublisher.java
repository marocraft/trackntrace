package com.github.marocraft.trackntrace.publish;

import java.util.HashMap;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

/**
 * Publish collected logs into a blocking queue to be processed by running
 * threads
 * 
 * @author Housseine TASSA
 * @author Sallah KOKAINA
 * 
 */
@Getter
@Component("logPublisher")
@Slf4j
public class LogPublisher implements ILogPublisher<String> {

	private BlockingQueue<HashMap<String, String>> logQueue = new LinkedBlockingQueue<>(50);

	@Override
	public void publish(String message,String loglevel) {
		try {
			HashMap<String, String>logElement= new HashMap<>();
			logElement.put(message, loglevel);
			logQueue.put(logElement);
		} catch (Exception ex) {
			log.error(message, ex);
		}
	}

	@Override
	public HashMap<String, String> get() throws InterruptedException {
		return logQueue.take();
	}

	public void clear() {
		logQueue.clear();
	}

	public int size() {
		return logQueue.size();
	}
}