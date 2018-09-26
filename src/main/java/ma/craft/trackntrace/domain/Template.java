package ma.craft.trackntrace.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter 
public class Template {
	
	private String format;
	private String logsPath;
	public Template(String format, String logspath) {
		super();
		this.format = format;
		this.logsPath = logspath;
	}
	public Template() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	

}


