package com.github.marocraft.trackntrace.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.stereotype.Component;

/**
 * Configuration loader using application.properties file
 * 
 * @author Houseine TASSA
 * @author Sallah KOKAINA
 * @author Khalid ELABBADI
 *
 */
@Component
@PropertySources(value = { 
		@PropertySource(value = "classpath:/application.properties", ignoreResourceNotFound = true),
		@PropertySource(value = "classpath:/application.yml", ignoreResourceNotFound = true)
})
public class ConfigurationTnT implements IConfigurationTnT {

	@Value("${tnt.logging.format}")
	private String format;

	@Value("${tnt.logging.output:json}")
	private String output;

	@Value("${tnt.multithread.poolsize:1}")
	Integer threadPoolSize;

	public ConfigurationTnT(String format, String output, Integer threadPoolSize) {
		super();
		this.format = format;
		this.output = output;
		this.threadPoolSize = threadPoolSize;
	}

	public ConfigurationTnT() {
		super();
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public String getOutput() {
		return output;
	}

	public void setOutput(String output) {
		this.output = output;
	}

	@Override
	public int getThreadPoolsize() {
		return threadPoolSize;
	}

	@Override
	public void setThreadPoolsize(int size) {
		threadPoolSize = size;
	}
}