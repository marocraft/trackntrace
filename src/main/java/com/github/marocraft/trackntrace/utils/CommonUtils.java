package com.github.marocraft.trackntrace.utils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;

import com.github.marocraft.trackntrace.annotation.Mapping;
import com.github.marocraft.trackntrace.domain.LogTrace;
import com.github.marocraft.trackntrace.domain.Variable;

/**
 * Utilities commonly used by the whole application
 * 
 * @author Khalid ELABBADI
 *
 */
@Component
public class CommonUtils {

	/**
	 * Replace each field in the template by its value
	 * 
	 * @param format
	 * @param field
	 * @param logTrace
	 * @return
	 * @throws IllegalAccessException
	 */
	public String replace(String format, String field, LogTrace logTrace) throws IllegalAccessException {
		Object valueOfField = valueOf(field, logTrace);

		return format.replaceAll("\"\\{\\{" + field + "\\}\\}\"", "\"" + valueOfField.toString() + "\"") + "";
	}

	/**
	 * Remove double-quoted and curly brackets from a string
	 * 
	 * @param format
	 * @return
	 */
	public String replaceDoubleQuotesPlusCurlyBracket(String format) {
		return format.replace("\"{", "{");
	}

	public List<Variable> extractVariables(String expression) {

		final String regex = "\\{\\{([0-9a-zA-Z_]+)\\}\\}";
		final Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
		final Matcher matcher = pattern.matcher(expression);
		final List<Variable> variables = new ArrayList<>(matcher.groupCount());

		while (matcher.find()) {
			Variable variable = new Variable();
			for (int i = 1; i <= matcher.groupCount(); i++) {
				variable.setName(matcher.group(i));
				variable.setStart(matcher.start());
				variable.setEnd(matcher.end());
				variables.add(variable);
			}
		}

		return variables;
	}

	public Object valueOf(String fieldName, LogTrace trace) throws IllegalAccessException {
		Field[] clazzFields = trace.getClass().getDeclaredFields();
		for (Field clazzField : clazzFields) {
			Mapping annotation = clazzField.getAnnotation(Mapping.class);
			if (annotation != null && annotation.field().equals(fieldName)) {
				clazzField.setAccessible(true);
				return clazzField.get(trace);
			}
		}

		return null;
	}

	public int getNumberOfVariablesFromFormatFile(String expression) {
		List<Variable> variables = extractVariables(expression);
		return variables.size();
	}
}