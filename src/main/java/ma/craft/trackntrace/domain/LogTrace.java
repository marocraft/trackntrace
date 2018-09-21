package ma.craft.trackntrace.domain;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class LogTrace {
	public long executionTime;
	public String methodName;
	public String className;
	public String logLevel;

	public LogTrace() {
		super();

	}

	public LogTrace(long executionTime, String methodName, String className, String logLevel) {
		super();
		this.executionTime = executionTime;
		this.methodName = methodName;
		this.className = className;
		this.logLevel = logLevel;
	}



}
