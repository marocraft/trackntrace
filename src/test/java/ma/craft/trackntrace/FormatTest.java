package ma.craft.trackntrace;

import java.io.File;
import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import ma.craft.trackntrace.domain.LogTrace;
import ma.craft.trackntrace.domain.Template;
import ma.craft.trackntrace.generate.RegExManager;
import ma.craft.trackntrace.generate.TemplateReader;

public class FormatTest {

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
	public void shouldHaveSameNumberTemplateVariablesAsClasssTemplateVariables() {
		LogTrace logTrace = new LogTrace();
		String format = TemplateReader.readTmplate();
		int nbrFieldsFromTemplateFile = RegExManager.getNumberOfVariablesFromFormatFile(format);
		int nbrFieldsFromTemplateClass = logTrace.getColumnCount();
		Assert.assertEquals(nbrFieldsFromTemplateClass, nbrFieldsFromTemplateFile);
	}
	
	
	// position des variable
	// noms des variables

}
