package com.github.marocraft.trackntrace.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:/application.yml")
public class TnTConfiguration {

	@Value("${tnt.logging.format}")
	private String format;

	@Value("${tnt.logging.file.path}")
	private String logsPath;

	public TnTConfiguration(String format, String logspath) {
		super();
		
		this.format = format;
		this.logsPath = logspath;
	}

	public TnTConfiguration() {
		super();
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public String getLogFilePath() {
		if (logsPath.substring(0, 1).contentEquals("\"")) {
			logsPath = logsPath.substring(1, logsPath.length() - 1);
		}

		return logsPath;
	}

	public void setLogFilePath(String logsPath) {
		this.logsPath = logsPath;
	}
}