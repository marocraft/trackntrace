package com.github.marocraft.trackntrace.domain;

/**
 * Define the logging level. This enumeration contains 4 possible values:
 * <ul>
 * <li>CRITICAL: Define that the being logged message is critical and should not
 * be ignored</li>
 * <li>IMPORTANT: Define that the being logged message is important and need
 * special care</li>
 * <li>NORMAL: Define that the being logged message is normal and no real
 * attention is required</li>
 * <li>TRIVIAL: Define that the being logged message is for information only and
 * no attention is required</li>
 * </ul>
 * 
 * 
 * @author Houseine TASSA
 * @author Salah KOKAINA
 * @since 0.0.3
 */
public enum LogLevel {

	/**
	 * Define that the being logged message is critical and should not be ignored
	 */
	CRITICAL,

	/**
	 * Define that the being logged message is important and need special care
	 */
	IMPORTANT,

	/**
	 * Define that the being logged message is normal and no real attention is
	 * required
	 */
	NORMAL,

	/**
	 * Define that the being logged message is for information only and no attention
	 * is required
	 */
	TRIVIAL;
}