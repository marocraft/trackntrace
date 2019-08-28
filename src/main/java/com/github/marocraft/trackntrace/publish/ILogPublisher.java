package com.github.marocraft.trackntrace.publish;

import java.util.Map;

/**
 * Interface for log publishing
 * 
 * @author Houseine TASSA
 * @author Sallah KOKAINA
 *
 * @param <E> is a generic type, used to make the type of message flexible
 */
public interface ILogPublisher<E> {


	/**
	 * Add a new message to the queue
	 * @param message is a generic param, could be String or others
	 * @param logLevel to precise the loglevel that will be used to log the message 
	 */
	public void publish(E message, String logLevel);

	
	/**
	 * Return current next queue item
	 * @return returns a Map that contains a message and log level
	 * @throws InterruptedException thrown when a thread is waiting, sleeping, or otherwise occupied, and the thread is interrupted 
	 */
	public Map<E, String> get() throws InterruptedException;

	/**
	 * Clear all queue items
	 * 
	 */
	public void clear();

	/**
	 * Return size of queue
	 * @return returns size of the queue
	 */
	public int size();
}