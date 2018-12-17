package ma.craft.trackntrace.generate;

import java.io.File;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import ma.craft.trackntrace.domain.Template;

public class TemplateReader {
	private static Logger logger = LoggerFactory.getLogger(TemplateReader.class);

	public static Template readTemplate() {
		ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
		try {
			File file = readFile();
			Template template = mapper.readValue(file, Template.class);
			return template;
		} catch (Exception e) {
			logger.error("Error while reading template", e);
		}
		return null;
	}

	public static File readFile() {
		ClassLoader classLoader = TemplateReader.class.getClassLoader();
		File file = new File(classLoader.getResource("tnt.yml").getFile());
		return file;
	}

	public static Template parse(File file) throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper(new YAMLFactory());

		return mapper.readValue(file, Template.class);
	}

}
