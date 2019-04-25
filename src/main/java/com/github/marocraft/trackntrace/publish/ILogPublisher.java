package com.github.marocraft.trackntrace.publish;

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
	 * @param logMessage
	 */
	public void publish(E message);

	/**
	 * Return current next queue item
	 * 
	 * @return
	 * @throws InterruptedException
	 */
	public E get() throws InterruptedException;

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