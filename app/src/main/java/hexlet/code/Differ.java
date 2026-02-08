package hexlet.code;

import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

public class Differ {
    public static String generate(String filePath1, String filePath2) throws IOException {
        ObjectMapper mapper = new ObjectMapper();

        String content1 = Files.readString(resolvePath(filePath1));
        String content2 = Files.readString(resolvePath(filePath2));

        Map<String, Object> data1 = mapper.readValue(content1, new TypeReference<>() { });
        Map<String, Object> data2 = mapper.readValue(content2, new TypeReference<>() { });

        return data1.toString() + System.lineSeparator() + data2.toString();
    }

    private static Path resolvePath(String filePath) {
        Path path = Paths.get(filePath);
        return path.isAbsolute() ? path : path.toAbsolutePath().normalize();
    }
}
