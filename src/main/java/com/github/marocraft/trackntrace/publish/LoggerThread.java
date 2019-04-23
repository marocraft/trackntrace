package com.github.marocraft.trackntrace.publish;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

/**
 * Logger thread body
 * 
 * @author Tassa Housseine
 */
@Slf4j
@Component
@Scope(value = BeanDefinition.SCOPE_PROTOTYPE)
public class LoggerThread implements Runnable {

	@Autowired
	ILogPublisher<String> publisher;

	@Override
	public void run() {
		String msg = null;
		try {
			if (publisher != null) {
				while ((msg = publisher.get()) != null) {
					log.info(msg);
					Thread.sleep(10);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage(), e);
		}
	}
}