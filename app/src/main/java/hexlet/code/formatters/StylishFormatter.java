package hexlet.code.formatters;

import hexlet.code.DiffNode;

import java.util.ArrayList;
import java.util.List;

public class StylishFormatter {
    public static String format(List<DiffNode> diff) {
        List<String> lines = new ArrayList<>();
        for (DiffNode node : diff) {
            switch (node.getStatus()) {
                case UNCHANGED -> lines.add(formatLine("    ", node.getKey(), node.getValue1()));
                case ADDED -> lines.add(formatLine("  + ", node.getKey(), node.getValue2()));
                case REMOVED -> lines.add(formatLine("  - ", node.getKey(), node.getValue1()));
                case CHANGED -> {
                    lines.add(formatLine("  - ", node.getKey(), node.getValue1()));
                    lines.add(formatLine("  + ", node.getKey(), node.getValue2()));
                }
                default -> throw new IllegalStateException("Unknown node status");
            }
        }

        return "{\n" + String.join("\n", lines) + "\n}";
    }

    private static String formatLine(String prefix, String key, Object value) {
        return prefix + key + ": " + value;
    }
}
