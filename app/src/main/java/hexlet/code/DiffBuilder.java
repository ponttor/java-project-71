package hexlet.code;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

public final class DiffBuilder {
    private DiffBuilder() {
    }

    public static List<DiffNode> build(Map<String, Object> data1, Map<String, Object> data2) {
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
