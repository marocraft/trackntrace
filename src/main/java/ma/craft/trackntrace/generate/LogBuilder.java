package ma.craft.trackntrace.generate;

import ma.craft.trackntrace.domain.LogTrace;

public class LogBuilder {

	public static String build(LogTrace logTrace) {
		StringBuffer logMessage = new StringBuffer();
		logMessage.append("classe : ");
		logMessage.append(logTrace.getClassName());
		logMessage.append(",");
		logMessage.append("name : ");
		logMessage.append(logTrace.getMethodName());
		logMessage.append(",");
		logMessage.append("level :  ");
		logMessage.append("(");
		logMessage.append(logTrace.getLogLevel());
		logMessage.append(")");
		logMessage.append(",");
		logMessage.append(" execution time: ");
		logMessage.append(logTrace.getExecutionTime());
		logMessage.append(" ms");
		return logMessage.toString();
	}
}
