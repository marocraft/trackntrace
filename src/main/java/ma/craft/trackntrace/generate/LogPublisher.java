package ma.craft.trackntrace.generate;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.MDC;

import lombok.Getter;

@Getter
public class LogPublisher {

	private static final LogPublisher INSTANCE = new LogPublisher();
	private List<String> logs = new ArrayList<>();

	private LogPublisher() {
	}

	public static LogPublisher instance() {
		return INSTANCE;
	}

	public void publish(String logMessage) {
		logs.add(logMessage);
	}

	public void clear() {
		logs.clear();
	}

	public Boolean empty() {
		return logs.isEmpty();
	}

	public Integer logStackSize() {
		return logs.size();
	}

	public void exportFile(String path, List<String> logs) throws IOException {

		PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(path, true)));

		for (String log : logs) {
			writer.write(log + "\r\n");
		}
		writer.close();
	}

	public void exportmdc(List<String> logs) {
		String logss = "";
		for (String log : logs) {
			logss = logss + log + "\n";
		}
		MDC.put("logs", logss);
	}
}
