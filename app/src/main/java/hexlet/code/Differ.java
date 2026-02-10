package hexlet.code;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

public class Differ {
    public static String generate(String filePath1, String filePath2) throws IOException {
        Path path1 = resolvePath(filePath1);
        Path path2 = resolvePath(filePath2);

        Map<String, Object> data1 = Parser.parse(path1);
        Map<String, Object> data2 = Parser.parse(path2);

        return buildDiff(data1, data2);
    }

    private static Path resolvePath(String filePath) {
        Path path = Paths.get(filePath);
        return path.isAbsolute() ? path : path.toAbsolutePath().normalize();
    }

    private static String buildDiff(Map<String, Object> data1, Map<String, Object> data2) {
        Set<String> keys = new TreeSet<>();
        keys.addAll(data1.keySet());
        keys.addAll(data2.keySet());

        List<String> lines = new ArrayList<>();
        for (String key : keys) {
            boolean inFirst = data1.containsKey(key);
            boolean inSecond = data2.containsKey(key);

            if (inFirst && inSecond) {
                Object value1 = data1.get(key);
                Object value2 = data2.get(key);
                if (Objects.equals(value1, value2)) {
                    lines.add(formatLine("    ", key, value1));
                } else {
                    lines.add(formatLine("  - ", key, value1));
                    lines.add(formatLine("  + ", key, value2));
                }
            } else if (inFirst) {
                lines.add(formatLine("  - ", key, data1.get(key)));
            } else {
                lines.add(formatLine("  + ", key, data2.get(key)));
            }
        }

        return "{\n" + String.join("\n", lines) + "\n}";
    }

    private static String formatLine(String prefix, String key, Object value) {
        return prefix + key + ": " + value;
    }
}
