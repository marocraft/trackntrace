package ma.craft.trackntrace.domain;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:/application.yml")
public class Template {

	@Value("${format}")
	private String format;
	@Value("${logsPath}")
	private String logsPath;

	public Template(String format, String logspath) {
		super();
		this.format = format;
		this.logsPath = logspath;
	}

	public Template() {
		super();

	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public String getLogsPath() {
		if (logsPath.substring(0, 1).contentEquals("\"")) {
			logsPath = logsPath.substring(1, logsPath.length() - 1);
		}

		return logsPath;
	}

	public void setLogsPath(String logsPath) {
		this.logsPath = logsPath;
	}

}
