package hexlet.code;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

public class Differ {
    public static String generate(String filePath1, String filePath2) throws Exception {
        return generate(filePath1, filePath2, "stylish");
    }

    public static String generate(String filePath1, String filePath2, String format) throws Exception {
        Map<String, Object> data1 = loadData(filePath1);
        Map<String, Object> data2 = loadData(filePath2);

        List<DiffNode> diff = DiffBuilder.build(data1, data2);
        return Formatter.format(diff, format);
    }

    private static Map<String, Object> loadData(String filePath) throws Exception {
        Path path = resolvePath(filePath);
        String content = Files.readString(path);
        String inputFormat = getInputFormat(path);
        return Parser.parse(content, inputFormat);
    }

    private static Path resolvePath(String filePath) {
        Path path = Paths.get(filePath);
        return path.isAbsolute() ? path : path.toAbsolutePath().normalize();
    }

    private static String getInputFormat(Path path) {
        String name = path.getFileName().toString();
        int lastDot = name.lastIndexOf('.');
        if (lastDot == -1 || lastDot == name.length() - 1) {
            return "";
        }
        return name.substring(lastDot + 1).toLowerCase();
    }

}
