package com.github.marocraft.trackntrace.publish;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Logger thread body
 * 
 * @author Tassa Housseine
 * @author Sallah KOKAINA
 * @author Khalid ELABBADI
 * 
 */
@Component
@Scope(value = BeanDefinition.SCOPE_PROTOTYPE)
public class LoggerThread implements Runnable {
	private final Logger log = LoggerFactory.getLogger(LoggerThread.class);
	@Autowired
	ILogPublisher<String> publisher;

	@Override
	public void run() {
		HashMap<String, String> msg = null;
		try {
			if (publisher != null) {
				while ((msg = (HashMap<String, String>) publisher.get()) != null) {
					Iterator<Entry<String, String>> hmIterator = msg.entrySet().iterator();
					String logMessage = "";

					String logLevel = "";
					while (hmIterator.hasNext()) {
						Entry<String, String> mapElement = hmIterator.next();

						logLevel = mapElement.getValue();
						logMessage = mapElement.getKey();

					}

					switch (logLevel) {
					case "TRACE":
						log.trace(logMessage);
						break;
					case "DEBUG":
						log.debug(logMessage);
						break;
					case "INFO":
						log.info(logMessage);
						break;
					case "WARN":
						log.warn(logMessage);
						break;
					case "ERROR":
						log.error(logMessage);
						break;

					default:
						break;
					}

					Thread.sleep(10);
				}
			}

		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
	}
}