package ma.craft.trackntrace.generate;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import lombok.extern.slf4j.Slf4j;
import ma.craft.trackntrace.domain.Template;

import java.io.File;
import java.io.IOException;

@Slf4j
public class TemplateReader {

    public static Template readTemplate() {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        try {
            File file = readFile();
            Template template = mapper.readValue(file, Template.class);
            return template;
        } catch (Exception e) {
            log.error("Error while reading template", e);
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
