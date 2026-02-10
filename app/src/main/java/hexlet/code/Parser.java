package hexlet.code;

import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;
import tools.jackson.dataformat.yaml.YAMLFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;

public class Parser {
    public static Map<String, Object> parse(Path path) throws IOException {
        String content = Files.readString(path);
        String extension = getFileExtension(path);
        ObjectMapper mapper = getMapper(extension);
        return mapper.readValue(content, new TypeReference<>() { });
    }

    private static ObjectMapper getMapper(String extension) {
        if ("yml".equals(extension) || "yaml".equals(extension)) {
            return new ObjectMapper(new YAMLFactory());
        }
        if ("json".equals(extension)) {
            return new ObjectMapper();
        }
        throw new IllegalArgumentException("Unsupported file extension: " + extension);
    }

    private static String getFileExtension(Path path) {
        String name = path.getFileName().toString();
        int lastDot = name.lastIndexOf('.');
        if (lastDot == -1 || lastDot == name.length() - 1) {
            return "";
        }
        return name.substring(lastDot + 1);
    }
}
