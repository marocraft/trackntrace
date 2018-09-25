package ma.craft.trackntrace.generate;

import java.util.List;

import ma.craft.trackntrace.domain.LogTrace;
import ma.craft.trackntrace.domain.Template;
import ma.craft.trackntrace.domain.Variable;

public class LogBuilder {

	public static String build(LogTrace logTrace) {
		Template template = TemplateReader.readTemplate();
		String format = template.getFormat();
		List<Variable> variables = RegExManager.extractVariables(format);
		format = format.replaceAll("'\\{\\{" + variables.get(0).getName() + "\\}\\}'", logTrace.getClassName());
		format = format.replaceAll("'\\{\\{" + variables.get(1).getName() + "\\}\\}'", logTrace.getMethodName());
		format = format.replaceAll("'\\{\\{" + variables.get(2).getName() + "\\}\\}'", logTrace.getLogLevel());
		format = format.replaceAll("'\\{\\{" + variables.get(3).getName() + "\\}\\}'",
				"" + logTrace.getExecutionTime());
		StringBuffer logMessage = new StringBuffer();
		logMessage.append(format);
		return logMessage.toString();
	}

}
