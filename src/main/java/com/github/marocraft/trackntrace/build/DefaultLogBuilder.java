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
@Component("defaultLogBuilder")
public class DefaultLogBuilder implements ILogBuilder {

	@Autowired
	@Qualifier("configurationTnTDefault")
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
	@Override
	@Qualifier("defaultLogTrace")
	public String build(LogTrace logTrace) throws IllegalAccessException {
		String format = config.getFormat();
		List<Variable> variables = commonUtils.extractVariables(format);
		for (Variable variable : variables) {
			try {
				format = commonUtils.replace(format, variable.getName(), logTrace);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return format;
	}
}