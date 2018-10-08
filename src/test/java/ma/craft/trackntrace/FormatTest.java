package ma.craft.trackntrace;

import java.io.File;
import java.io.IOException;
import java.util.List;
import ma.craft.trackntrace.domain.Variable;
import org.junit.Assert;
import org.junit.Test;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import ma.craft.trackntrace.domain.Template;
import ma.craft.trackntrace.generate.RegExManager;
import ma.craft.trackntrace.generate.TemplateReader;

public class FormatTest {

	String format = "{methodName: '{{methodName}}',className: '{{className}}',logLevel: '{{logLevel}}', executionTime: '{{executionTime}}' ms,logMessage: '{{executionTime}}'}";

	@Test
	public void shouldFormatBeNotNull() {
		File file = TemplateReader.readFile();
		Assert.assertNotNull(file);
	}

	@Test
	public void shouldCreateTemxplateObject() throws JsonParseException, JsonMappingException, IOException {
		File file = TemplateReader.readFile();
		Template template = TemplateReader.parse(file);
		Assert.assertNotNull(template);
		Assert.assertNotNull(template.getFormat());
	}

	@Test
	public void shouldHaveExpectedNumber() throws JsonParseException, JsonMappingException, IOException {
		File file = TemplateReader.readFile();
		Template template = TemplateReader.parse(file);
		Assert.assertNotNull(template);
		Assert.assertNotNull(template.getFormat());
	}

	@Test
	public void shouldHaveRightNumberTemplateVariables() {
		int nbrFieldsFromTemplateFile = RegExManager.getNumberOfVariablesFromFormatFile(format);
		Assert.assertEquals(5, nbrFieldsFromTemplateFile);
	}

	@Test
	public void shouldHaveNoVariables() {
		List<Variable> variables = RegExManager.extractVariables("No variables here");
		Assert.assertNotNull(variables);
		Assert.assertEquals(0, variables.size());
	}

	@Test
	public void shouldHaveVariablesWithCorrectPositions() {
		List<Variable> variables = RegExManager.extractVariables(format);
		Variable variable = variables.get(0);
		Assert.assertEquals(14, variable.getStart());
		Assert.assertEquals(26, variable.getStart() + variable.getName().length() + 2);
	}

	@Test
	public void shouldHaveCorrectNames() {
		List<Variable> variables = RegExManager.extractVariables(format);
		Assert.assertEquals("className", variables.get(1).getName());
	}

}
