package com.github.marocraft.trackntrace.build;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.github.marocraft.trackntrace.config.IConfigurationTnT;
import com.github.marocraft.trackntrace.domain.LogTrace;
import com.github.marocraft.trackntrace.domain.Variable;
import com.github.marocraft.trackntrace.utils.CommonUtils;

/**
 * Build a log line from a from LogTrace object
 * 
 * @author Houseine TASSA
 * @author Sallah KOKAINA
 * @author Khalid ELABBADI
 *
 */
@Component("restLogBuilder")
public class RestLogBuilder implements ILogBuilder {

	@Autowired
	@Qualifier("configurationTnTRest")
	IConfigurationTnT configRest;

	@Autowired
	CommonUtils commonUtils;

	/**
	 * Construct logs from LogTrace object
	 * 
	 * @param logTrace a trace of logs
	 * @return a string of logs
	 * @throws IllegalAccessException An IllegalAccessException is thrown when an application tries to reflectively create an instance (other than an array), set or get a field, or invoke a method
	 */
	@Override
	@Qualifier("restLogTrace")
	public String build(LogTrace logTrace) throws IllegalAccessException {
		String format = configRest.getFormat();
		List<Variable> variables = commonUtils.extractVariables(format);
		for (Variable variable : variables) {
				format = commonUtils.replace(format, variable.getName(), logTrace);
		}

		return format;
	}

}