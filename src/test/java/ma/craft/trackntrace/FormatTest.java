package ma.craft.trackntrace;

import java.io.IOException;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.fasterxml.jackson.core.JsonParseException;

import ma.craft.trackntrace.context.SpringAOPContext;
import ma.craft.trackntrace.domain.Template;
import ma.craft.trackntrace.domain.Variable;
import ma.craft.trackntrace.generate.RegExManager;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringAOPContext.class)
public class FormatTest {

	String format = "{methodName: '{{methodName}}',className: '{{className}}',logLevel: '{{logLevel}}', executionTime: '{{executionTime}}' ms,logMessage: '{{executionTime}}'}";
	@Autowired
	Template template;

	@Test
	public void shouldCreateTemxplateObject() throws JsonParseException, IOException {
		Assert.assertNotNull(template);
		Assert.assertNotNull(template.getFormat());
	}

	@Test
	public void shouldHaveExpectedNumber() throws JsonParseException, IOException {
		Assert.assertNotNull(template);
		Assert.assertNotNull(template.getFormat());
		template.getFormat();
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
