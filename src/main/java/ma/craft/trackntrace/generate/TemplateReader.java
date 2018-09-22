package ma.craft.trackntrace.generate;

import java.io.File;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.core.io.Resource;
import org.springframework.util.ResourceUtils;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import ma.craft.trackntrace.domain.LogTrace;
import ma.craft.trackntrace.domain.Template;

public class TemplateReader {

    public static Template readTemplate() {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        try {
            File file = readFile();
            Template template = mapper.readValue(file, Template.class);
            return template;
        } catch (Exception e) {

            e.printStackTrace();
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
