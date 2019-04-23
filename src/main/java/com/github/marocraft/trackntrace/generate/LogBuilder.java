package com.github.marocraft.trackntrace.generate;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.marocraft.trackntrace.collect.ValueCollector;
import com.github.marocraft.trackntrace.config.TnTConfiguration;
import com.github.marocraft.trackntrace.domain.LogTrace;
import com.github.marocraft.trackntrace.domain.Variable;

@Component
public class LogBuilder {

	@Autowired
	TnTConfiguration config;

	/**
	 * Construct logs from Logtrace object
	 * 
	 * @param logTrace
	 * @return
	 * @throws IllegalAccessException
	 */
	public String build(LogTrace logTrace) throws IllegalAccessException {
		String format = config.getFormat();
		List<Variable> variables = RegExManager.extractVariables(format);
		for (Variable variable : variables) {
			format = replace(format, variable.getName(), logTrace);
		}
		format = replaceDoubleQuotesPlusCurlyBracket(format);

		return format;
	}

	/**
	 * Replace each field in the template by its value
	 * 
	 * @param format
	 * @param field
	 * @param logTrace
	 * @return
	 * @throws IllegalAccessException
	 */
	public static String replace(String format, String field, LogTrace logTrace) throws IllegalAccessException {
		Object valueOfField = ValueCollector.valueOf(field, logTrace);
		
		return format.replaceAll("\"\\{\\{" + field + "\\}\\}\"", "\"" + valueOfField.toString() + "\"") + "";
	}

	public static String replaceDoubleQuotesPlusCurlyBracket(String format) {
		return format.replace("\"{", "{");
	}
}