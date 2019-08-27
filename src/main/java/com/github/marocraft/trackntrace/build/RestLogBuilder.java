package com.github.marocraft.trackntrace.build;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.github.marocraft.trackntrace.config.IConfigurationTnT;
import com.github.marocraft.trackntrace.domain.LogTrace;
import com.github.marocraft.trackntrace.domain.Variable;
import com.github.marocraft.trackntrace.utils.CommonUtils;

import lombok.extern.slf4j.Slf4j;

/**
 * Build a log line from a from LogTrace object
 * 
 * @author Houseine TASSA
 * @author Sallah KOKAINA
 * @author Khalid ELABBADI
 *
 */
@Slf4j
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
	 * @param logTrace
	 * @return
	 * @throws IllegalAccessException
	 */
	@Override
	@Qualifier("restLogTrace")
	public String build(LogTrace logTrace) throws IllegalAccessException {
		String format = configRest.getFormat();
		List<Variable> variables = commonUtils.extractVariables(format);
		for (Variable variable : variables) {
			try {
				format = commonUtils.replace(format, variable.getName(), logTrace);
			} catch (Exception e) {
				log.debug("Invalid Arguments");
			}
		}

		return format;
	}

}