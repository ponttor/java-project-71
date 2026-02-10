package hexlet.code;

import java.util.List;

public class Formatter {
    public static String format(List<DiffNode> diff, String format) {
        if ("stylish".equals(format)) {
            return StylishFormatter.format(diff);
        }
        throw new IllegalArgumentException("Unsupported format: " + format);
    }
}
