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
        return generate(filePath1, filePath2, "stylish");
    }

    public static String generate(String filePath1, String filePath2, String format) throws IOException {
        Path path1 = resolvePath(filePath1);
        Path path2 = resolvePath(filePath2);

        Map<String, Object> data1 = Parser.parse(path1);
        Map<String, Object> data2 = Parser.parse(path2);

        List<DiffNode> diff = buildDiff(data1, data2);
        return Formatter.format(diff, format);
    }

    private static Path resolvePath(String filePath) {
        Path path = Paths.get(filePath);
        return path.isAbsolute() ? path : path.toAbsolutePath().normalize();
    }

    private static List<DiffNode> buildDiff(Map<String, Object> data1, Map<String, Object> data2) {
        Set<String> keys = new TreeSet<>();
        keys.addAll(data1.keySet());
        keys.addAll(data2.keySet());

        List<DiffNode> nodes = new ArrayList<>();
        for (String key : keys) {
            boolean inFirst = data1.containsKey(key);
            boolean inSecond = data2.containsKey(key);

            if (inFirst && inSecond) {
                Object value1 = data1.get(key);
                Object value2 = data2.get(key);
                if (Objects.equals(value1, value2)) {
                    nodes.add(DiffNode.unchanged(key, value1));
                } else {
                    nodes.add(DiffNode.changed(key, value1, value2));
                }
            } else if (inFirst) {
                nodes.add(DiffNode.removed(key, data1.get(key)));
            } else {
                nodes.add(DiffNode.added(key, data2.get(key)));
            }
        }

        return nodes;
    }
}
