package ma.craft.trackntrace.domain;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
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

}
