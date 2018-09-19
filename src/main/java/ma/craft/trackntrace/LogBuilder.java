package ma.craft.trackntrace;

import ma.craft.trackntrace.domain.LogTrace;

public class LogBuilder {

	public static String build(LogTrace logTrace) {
		StringBuffer logMessage = new StringBuffer();
		logMessage.append("classe : ");
		logMessage.append(logTrace.className);
		logMessage.append(",");
		logMessage.append("name : ");
		logMessage.append(logTrace.methodName);
		logMessage.append(",");
		logMessage.append("level :  ");
		logMessage.append("(");
		logMessage.append(logTrace.logLevel);
		logMessage.append(")");
		logMessage.append(",");
		logMessage.append(" execution time: ");
		logMessage.append(logTrace.executionTime);
		logMessage.append(" ms");
		return logMessage.toString();
	}
}
