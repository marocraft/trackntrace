package ma.craft.trackntrace.generate;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class RegExManager {

	private static int varibalesNumbe=0;
	
	public static void extractVariables(String expression) {
		
		final String regex = "(\\{\\{[0-9a-zA-Z_]+\\}\\})";
		final Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
		final Matcher matcher = pattern.matcher(expression);

		while (matcher.find()) {
		    for (int i = 1; i <= matcher.groupCount(); i++) {
		        System.out.println( matcher.group(i));
		        varibalesNumbe++;
		    }
		}
		
	}
	
	public static int getNumberOfVariablesFromFormatFile(String expression) {
		extractVariables(expression);
		return varibalesNumbe;
	}
	
}
