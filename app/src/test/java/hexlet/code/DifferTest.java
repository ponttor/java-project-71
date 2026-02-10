package hexlet.code;

import org.junit.jupiter.api.Test;

import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DifferTest {

    private Path getFixturePath(String name) throws URISyntaxException {
        return Paths.get(
            Objects.requireNonNull(getClass().getResource("/fixtures/" + name)).toURI()
        );
    }

    private String getFixtureContent(String name) throws Exception {
        return Files.readString(getFixturePath("expected/" + name)).trim();
    }

    @Test
    void testJsonNestedDiff() throws Exception {
        Path file1 = getFixturePath("nested1.json");
        Path file2 = getFixturePath("nested2.json");

        String expected = getFixtureContent("expected_stylish.txt");

        assertEquals(expected, Differ.generate(file1.toString(), file2.toString()));
    }

    @Test
    void testYamlNestedDiff() throws Exception {
        Path file1 = getFixturePath("file1.yml");
        Path file2 = getFixturePath("file2.yml");

        String expected = getFixtureContent("expected_stylish.txt");

        assertEquals(expected, Differ.generate(file1.toString(), file2.toString()));
    }

    @Test
    void testPlainFormat() throws Exception {
        Path file1 = getFixturePath("nested1.json");
        Path file2 = getFixturePath("nested2.json");

        String expected = getFixtureContent("expected_plain.txt");

        assertEquals(expected, Differ.generate(file1.toString(), file2.toString(), "plain"));
    }
}
