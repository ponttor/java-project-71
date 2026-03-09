package hexlet.code.formatters;

import hexlet.code.DiffNode;
import tools.jackson.databind.ObjectMapper;

import java.util.List;

public class JsonFormatter {
    public static String format(List<DiffNode> diff) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(diff);
    }
}
