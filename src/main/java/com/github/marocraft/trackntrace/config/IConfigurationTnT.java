package com.github.marocraft.trackntrace.config;

/**
 * Group and unify required configuration parameters for the framework.
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
	 * @param format
	 */
	public void setThreadPoolsize(int size);
}