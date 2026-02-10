package hexlet.code.formatters;

import hexlet.code.DiffNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PlainFormatter {
    public static String format(List<DiffNode> diff) {
        List<String> lines = new ArrayList<>();
        for (DiffNode node : diff) {
            switch (node.getStatus()) {
                case ADDED -> lines.add(
                    "Property '" + node.getKey() + "' was added with value: " + formatValue(node.getValue2())
                );
                case REMOVED -> lines.add("Property '" + node.getKey() + "' was removed");
                case CHANGED -> lines.add(
                    "Property '" + node.getKey() + "' was updated. From "
                        + formatValue(node.getValue1()) + " to " + formatValue(node.getValue2())
                );
                case UNCHANGED -> {
                    // skip
                }
                default -> throw new IllegalStateException("Unknown node status");
            }
        }

        return String.join("\n", lines);
    }

    private static String formatValue(Object value) {
        if (value == null) {
            return "null";
        }
        if (value instanceof Map || value instanceof List) {
            return "[complex value]";
        }
        if (value instanceof String) {
            return "'" + value + "'";
        }
        return value.toString();
    }
}
