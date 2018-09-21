package ma.craft.trackntrace.generate;

import java.io.File;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.core.io.Resource;
import org.springframework.util.ResourceUtils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import ma.craft.trackntrace.domain.LogTrace;

public class TemplateReader {

	public static void main(String[] args) {
		ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
		try {

			File file = fileLoader();
			LogTrace logTrace = mapper.readValue(file, LogTrace.class);
			System.out.println(
					"housseine " + ReflectionToStringBuilder.toString(logTrace, ToStringStyle.MULTI_LINE_STYLE));
		} catch (Exception e) {

			e.printStackTrace();
		}

	}

	public static File fileLoader() {
		ClassLoader classLoader = TemplateReader.class.getClassLoader();
		File file = new File(classLoader.getResource("tnt.yml").getFile());
		return file;
	}

}
