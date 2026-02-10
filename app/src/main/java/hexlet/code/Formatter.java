package hexlet.code;

import hexlet.code.formatters.PlainFormatter;
import hexlet.code.formatters.JsonFormatter;
import hexlet.code.formatters.StylishFormatter;

import java.util.List;

public class Formatter {
    public static String format(List<DiffNode> diff, String format) {
        if ("json".equals(format)) {
            return JsonFormatter.format(diff);
        }
        if ("plain".equals(format)) {
            return PlainFormatter.format(diff);
        }
        if ("stylish".equals(format)) {
            return StylishFormatter.format(diff);
        }
        throw new IllegalArgumentException("Unsupported format: " + format);
    }
}
