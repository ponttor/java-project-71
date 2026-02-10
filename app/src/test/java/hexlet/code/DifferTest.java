package hexlet.code;

import org.junit.jupiter.api.Test;

import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DifferTest {

    private Path getFixturePath(String name) throws URISyntaxException {
        return Paths.get(
            Objects.requireNonNull(getClass().getResource("/fixtures/" + name)).toURI()
        );
    }

    @Test
    void testJsonNestedDiff() throws Exception {
        Path file1 = getFixturePath("nested1.json");
        Path file2 = getFixturePath("nested2.json");

        String expected = String.join("\n",
            "{",
            "    chars1: [a, b, c]",
            "  - chars2: [d, e, f]",
            "  + chars2: false",
            "  - checked: false",
            "  + checked: true",
            "  - default: null",
            "  + default: [value1, value2]",
            "  - id: 45",
            "  + id: null",
            "  - key1: value1",
            "  + key2: value2",
            "    numbers1: [1, 2, 3, 4]",
            "  - numbers2: [2, 3, 4, 5]",
            "  + numbers2: [22, 33, 44, 55]",
            "  - numbers3: [3, 4, 5]",
            "  + numbers4: [4, 5, 6]",
            "  + obj1: {nestedKey=value, isNested=true}",
            "  - setting1: Some value",
            "  + setting1: Another value",
            "  - setting2: 200",
            "  + setting2: 300",
            "  - setting3: true",
            "  + setting3: none",
            "}"
        );

        assertEquals(expected, Differ.generate(file1.toString(), file2.toString()));
    }

    @Test
    void testYamlNestedDiff() throws Exception {
        Path file1 = getFixturePath("file1.yml");
        Path file2 = getFixturePath("file2.yml");

        String expected = String.join("\n",
            "{",
            "    chars1: [a, b, c]",
            "  - chars2: [d, e, f]",
            "  + chars2: false",
            "  - checked: false",
            "  + checked: true",
            "  - default: null",
            "  + default: [value1, value2]",
            "  - id: 45",
            "  + id: null",
            "  - key1: value1",
            "  + key2: value2",
            "    numbers1: [1, 2, 3, 4]",
            "  - numbers2: [2, 3, 4, 5]",
            "  + numbers2: [22, 33, 44, 55]",
            "  - numbers3: [3, 4, 5]",
            "  + numbers4: [4, 5, 6]",
            "  + obj1: {nestedKey=value, isNested=true}",
            "  - setting1: Some value",
            "  + setting1: Another value",
            "  - setting2: 200",
            "  + setting2: 300",
            "  - setting3: true",
            "  + setting3: none",
            "}"
        );

        assertEquals(expected, Differ.generate(file1.toString(), file2.toString()));
    }
}
