package hexlet.code;

import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;
import tools.jackson.dataformat.yaml.YAMLFactory;

import java.util.Map;

public class Parser {
    public static Map<String, Object> parse(String content, String format) throws Exception {
        ObjectMapper mapper = getMapper(format);
        return mapper.readValue(content, new TypeReference<>() { });
    }

    private static ObjectMapper getMapper(String format) {
        if ("yml".equals(format) || "yaml".equals(format)) {
            return new ObjectMapper(new YAMLFactory());
        }
        if ("json".equals(format)) {
            return new ObjectMapper();
        }
        throw new IllegalArgumentException("Unsupported format: " + format);
    }
}
