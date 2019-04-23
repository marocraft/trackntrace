package com.github.marocraft.trackntrace.generate;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.github.marocraft.trackntrace.domain.Variable;

import lombok.Getter;
import lombok.Setter;

/**
 * Factory of regular expressions for extracting and processing variables
 * 
 * @author Houseine TASSA
 *
 */
@Getter
@Setter
public class RegExManager {

	/**
	 * Just for hiding public default constructor
	 */
	private RegExManager() {

	}

	public static List<Variable> extractVariables(String expression) {

		final String regex = "\\{\\{([0-9a-zA-Z_]+)\\}\\}";
		final Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
		final Matcher matcher = pattern.matcher(expression);
		final List<Variable> variables = new ArrayList<Variable>(matcher.groupCount());

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

	public static int getNumberOfVariablesFromFormatFile(String expression) {
		List<Variable> variables = extractVariables(expression);
		return variables.size();
	}
}