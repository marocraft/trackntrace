package com.github.marocraft.trackntrace.publish;

import java.util.HashMap;

/**
 * Interface for log publishing
 * 
 * @author Houseine TASSA
 * @author Sallah KOKAINA
 *
 * @param <E>
 */
public interface ILogPublisher<E> {

	
	/**
	 * Add a new message to the queue
	 * 
	 * @param message
	 * @param string 
	 */
	public void publish(E message, String string);

	
	/**
	 * Return current next queue item
	 * @return
	 * @throws InterruptedException
	 */
	public HashMap<E, String> get() throws InterruptedException;

	/**
	 * Clear all queue items
	 * 
	 */
	public void clear();

	/**
	 * Return size of queue
	 * @return
	 */
	public int size();
}