package com.example.demo.entities;

import org.aspectj.lang.ProceedingJoinPoint;

public class LogBuilder {

	public static String build(String className, 
			String methodName,
			Object[] args, 
			long executionTime) {
		StringBuffer logMessage = new StringBuffer();
		logMessage.append("classe : ");
		logMessage.append(className);
		logMessage.append(",");
		logMessage.append("name : ");
		logMessage.append(methodName);
		logMessage.append(",");
		logMessage.append("arguments :  ");
		logMessage.append("(");
		// append args
		for (int i = 0; i < args.length; i++) {
			logMessage.append(args[i]).append(",");
		}
		if (args.length > 0) {
			logMessage.deleteCharAt(logMessage.length() - 1);
		}

		logMessage.append(")");
		logMessage.append(",");
		logMessage.append(" execution time: ");
		logMessage.append(executionTime);
		logMessage.append(" ms");
		return logMessage.toString();
	}
}
