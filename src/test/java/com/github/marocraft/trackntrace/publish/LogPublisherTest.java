/**
 * 
 */
package com.github.marocraft.trackntrace.publish;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;

/**
 * 
 *
 */
public class LogPublisherTest {

	private ILogPublisher<String> logPublisher;

	@Before
	public void before() {
		logPublisher = new LogPublisher();
	}

	/**
	 * Test method for
	 * {@link com.github.marocraft.trackntrace.publish.LogPublisher#publish(java.lang.String, java.lang.String)}.
	 */
	@Test
	public void shouldPublish() {

		try {
			logPublisher.publish("logs", "INFO");
			HashMap<String, String> log = (HashMap<String, String>) logPublisher.get();
			assertNotNull(log);
			assertEquals(1, log.size());
			assertEquals("INFO", log.get("logs"));
			logPublisher.clear();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void shouldRemoveLogFromQeue() {
		logPublisher.publish("logs", "INFO");
		logPublisher.clear();
		assertEquals(0,logPublisher.size());
	}
	
	
	public void shouldGetSize() {
		logPublisher.publish("logs", "INFO");
		assertEquals(1, logPublisher.size());
	}
}
