package ma.craft.trackntrace.publish;

public interface ILogPublisher {
	
	/**
	 * @param logMessage
	 */
	public void publish(String logMessage);
}