package com.github.marocraft.trackntrace;

import java.io.IOException;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.fasterxml.jackson.core.JsonParseException;
import com.github.marocraft.trackntrace.config.IConfigurationTnT;
import com.github.marocraft.trackntrace.context.SpringAOPContext;
import com.github.marocraft.trackntrace.domain.Variable;
import com.github.marocraft.trackntrace.generate.CommonUtils;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringAOPContext.class)
public class FormatTest {

	@Autowired
	IConfigurationTnT config;

	@Autowired
	CommonUtils commonUtils;

	String format = "{methodName: '{{methodName}}',className: '{{className}}',logLevel: '{{logLevel}}', "
			+ "executionTime: '{{executionTime}}' ms,logMessage: '{{executionTime}}'}";

	@Test
	public void shouldCreateTemxplateObject() throws JsonParseException, IOException {
		Assert.assertNotNull(config);
		Assert.assertNotNull(config.getFormat());
	}

	@Test
	public void shouldHaveExpectedNumber() throws JsonParseException, IOException {
		Assert.assertNotNull(config);
		Assert.assertNotNull(config.getFormat());
		config.getFormat();
	}

	@Test
	public void shouldHaveRightNumberTemplateVariables() {
		int nbrFieldsFromTemplateFile = commonUtils.getNumberOfVariablesFromFormatFile(format);
		Assert.assertEquals(5, nbrFieldsFromTemplateFile);
	}

	@Test
	public void shouldHaveNoVariables() {
		List<Variable> variables = commonUtils.extractVariables("No variables here");
		Assert.assertNotNull(variables);
		Assert.assertEquals(0, variables.size());
	}

	@Test
	public void shouldHaveVariablesWithCorrectPositions() {
		List<Variable> variables = commonUtils.extractVariables(format);
		Variable variable = variables.get(0);
		Assert.assertEquals(14, variable.getStart());
		Assert.assertEquals(26, variable.getStart() + variable.getName().length() + 2);
	}

	@Test
	public void shouldHaveCorrectNames() {
		List<Variable> variables = commonUtils.extractVariables(format);
		Assert.assertEquals("className", variables.get(1).getName());
	}
}