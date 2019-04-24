package com.github.marocraft.trackntrace.generate;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.marocraft.trackntrace.config.IConfigurationTnT;
import com.github.marocraft.trackntrace.domain.LogTrace;
import com.github.marocraft.trackntrace.domain.Variable;

@Component
public class LogBuilderJSON implements ILogBuilder {

	@Autowired
	IConfigurationTnT config;

	@Autowired
	CommonUtils commonUtils;

	/**
	 * Construct logs from LogTrace object
	 * 
	 * @param logTrace
	 * @return
	 * @throws IllegalAccessException
	 */
	public String build(LogTrace logTrace) throws IllegalAccessException {
		String format = config.getFormat();
		List<Variable> variables = commonUtils.extractVariables(format);
		for (Variable variable : variables) {
			format = commonUtils.replace(format, variable.getName(), logTrace);
		}

		return format;
	}
}