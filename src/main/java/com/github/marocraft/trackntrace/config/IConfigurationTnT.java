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
	 * @return
	 */
	public String getFormat();

	/**
	 * Set Logging message pattern
	 * 
	 * @param format
	 */
	public void setFormat(String format);

	/**
	 * Return Logging message pattern
	 * 
	 * @return
	 */
	public int getThreadPoolsize();

	/**
	 * Set Logging message pattern
	 * 
	 * @param size
	 */
	public void setThreadPoolsize(int size);
	
	
	public String getSpanIdName();
	
	public String getTraceIdName();
	
	public String getParentSpanIdName();

}