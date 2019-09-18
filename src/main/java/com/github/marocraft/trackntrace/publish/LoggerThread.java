package com.github.marocraft.trackntrace.publish;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

/**
 * Logger thread body
 * 
 * @author Tassa Housseine
 * @author Sallah KOKAINA
 * @author Khalid ELABBADI
 * 
 */
@Slf4j
@Component
@Scope(value = BeanDefinition.SCOPE_PROTOTYPE)
public class LoggerThread implements Runnable {

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