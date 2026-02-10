package hexlet.code.formatters;

import hexlet.code.DiffNode;
import tools.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class JsonFormatter {
    public static String format(List<DiffNode> diff) {
        List<Map<String, Object>> result = new ArrayList<>();
        for (DiffNode node : diff) {
            Map<String, Object> entry = new LinkedHashMap<>();
            entry.put("key", node.getKey());
            entry.put("type", node.getStatus().name().toLowerCase());

            switch (node.getStatus()) {
                case UNCHANGED -> entry.put("value", node.getValue1());
                case ADDED -> entry.put("value", node.getValue2());
                case REMOVED -> entry.put("value", node.getValue1());
                case CHANGED -> {
                    entry.put("value1", node.getValue1());
                    entry.put("value2", node.getValue2());
                }
                default -> throw new IllegalStateException("Unknown node status");
            }
            result.add(entry);
        }

        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(result);
        } catch (Exception e) {
            throw new RuntimeException("Failed to format json", e);
        }
    }
}
