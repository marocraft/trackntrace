package ma.craft.trackntrace.publish;

public interface ILogPublisher<E> {

	/**
	 * Add a new message to the queue
	 * 
	 * @param logMessage
	 */
	public void publish(E message);

	public E get() throws InterruptedException;

	public void clear();

	public int size();
}