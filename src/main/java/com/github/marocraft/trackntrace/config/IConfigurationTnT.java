package com.github.marocraft.trackntrace.config;

/**
 * Interface for grouping and unifying required configuration parameters for the
 * framework.
 * 
 * @author Khalid ELABBADI
 *
 */
public interface IConfigurationTnT {

	/**
	 * Return Logging message pattern
	 * 
	 * @return format from the config file
	 */
	public String getFormat();

	/**
	 * Set Logging message pattern
	 * 
	 * @param format is the new pattern of format
	 */
	public void setFormat(String format);

	/**
	 * Return Logging message pattern
	 * 
	 * @return is an integer to get the size of the thread pool
	 */
	public int getThreadPoolsize();

	/**
	 * Set Logging message pattern
	 * 
	 * @param Integer to eddit size of the thread pool
	 */
	public void setThreadPoolsize(int size);
	
	
	/**
	 * Get Span id name from the config file
	 * @return the name of the spanid
	 */
	public String getSpanIdName();
	
	/**
	 * Get the traceid name from the config file
	 * @return  the name of the traceid
	 */
	public String getTraceIdName();
	
	/**
	 * Get the parent spanid name from the config file
	 * @return the name of the parent spanid name
	 */
	public String getParentSpanIdName();

}