package ma.craft.trackntrace.generate;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ma.craft.trackntrace.collect.ValueCollector;
import ma.craft.trackntrace.domain.LogTrace;
import ma.craft.trackntrace.domain.Template;
import ma.craft.trackntrace.domain.Variable;

@Component
public class LogBuilder {

	@Autowired
	Template template;

	/**
	 * permet de construire le log à partir de Logtrace
	 * 
	 * @param logTrace
	 * @return
	 * @throws IllegalAccessException
	 */
	public String build(LogTrace logTrace) throws IllegalAccessException {
		String format = template.getFormat();
		List<Variable> variables = RegExManager.extractVariables(format);
		for (Variable variable : variables) {
			format = replace(format, variable.getName(), logTrace);
		}
		return format;
	}

	/**
	 * Remplace chaque champs dans la template par ca valeur
	 * 
	 * @param format
	 * @param field
	 * @param logTrace
	 * @return
	 * @throws IllegalAccessException
	 */
	static String replace(String format, String field, LogTrace logTrace) throws IllegalAccessException {
		Object valueOfField = ValueCollector.valueOf(field, logTrace);
		return format.replaceAll("'\\{\\{" + field + "\\}\\}'", "" + valueOfField.toString()) + "";

	}

}
