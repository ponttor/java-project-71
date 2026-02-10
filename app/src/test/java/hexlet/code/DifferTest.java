package hexlet.code;

import org.junit.jupiter.api.Test;

import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DifferTest {

    private Path getFixturePath(String name) throws URISyntaxException {
        return Paths.get(
            Objects.requireNonNull(getClass().getResource("/fixtures/" + name)).toURI()
        );
    }

    @Test
    void testIdenticalJson() throws Exception {
        Path file1 = getFixturePath("identical1.json");
        Path file2 = getFixturePath("identical2.json");

        String expected = String.join("\n",
            "{",
            "    a: 1",
            "    b: two",
            "}"
        );

        assertEquals(expected, Differ.generate(file1.toString(), file2.toString()));
    }

    @Test
    void testAddedDeletedChanged() throws Exception {
        Path file1 = getFixturePath("added_deleted1.json");
        Path file2 = getFixturePath("added_deleted2.json");

        String expected = String.join("\n",
            "{",
            "  - a: 1",
            "  - b: 2",
            "  + b: 3",
            "  + c: 4",
            "}"
        );

        assertEquals(expected, Differ.generate(file1.toString(), file2.toString()));
    }

    @Test
    void testNullAndTypeChange() throws Exception {
        Path file1 = getFixturePath("null_type1.json");
        Path file2 = getFixturePath("null_type2.json");

        String expected = String.join("\n",
            "{",
            "    a: null",
            "  - b: 1",
            "  + b: 1",
            "}"
        );

        assertEquals(expected, Differ.generate(file1.toString(), file2.toString()));
    }

    @Test
    void testEmptyAndNonEmpty() throws Exception {
        Path file1 = getFixturePath("empty.json");
        Path file2 = getFixturePath("nonempty.json");

        String expected = String.join("\n",
            "{",
            "  + a: 1",
            "}"
        );

        assertEquals(expected, Differ.generate(file1.toString(), file2.toString()));
    }

    @Test
    void testInvalidJson() throws Exception {
        Path file1 = getFixturePath("identical1.json");
        Path file2 = getFixturePath("invalid.json");

        assertThrows(Exception.class, () -> Differ.generate(file1.toString(), file2.toString()));
    }

    @Test
    void testYamlDiff() throws Exception {
        Path file1 = getFixturePath("file1.yml");
        Path file2 = getFixturePath("file2.yml");

        String expected = String.join("\n",
            "{",
            "  - follow: false",
            "    host: hexlet.io",
            "  - proxy: 123.234.53.22",
            "  - timeout: 50",
            "  + timeout: 20",
            "  + verbose: true",
            "}"
        );

        assertEquals(expected, Differ.generate(file1.toString(), file2.toString()));
    }
}
