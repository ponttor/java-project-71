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
    void testJsonDefaultFormat() throws Exception {
        assertDefaultMatchesStylish("nested1.json", "nested2.json");
    }

    @Test
    void testJsonStylishFormat() throws Exception {
        assertFormat("nested1.json", "nested2.json", "stylish", "expected_stylish.txt");
    }

    @Test
    void testJsonPlainFormat() throws Exception {
        assertFormat("nested1.json", "nested2.json", "plain", "expected_plain.txt");
    }

    @Test
    void testJsonJsonFormat() throws Exception {
        assertFormat("nested1.json", "nested2.json", "json", "expected_json.txt");
    }

    @Test
    void testYamlDefaultFormat() throws Exception {
        assertDefaultMatchesStylish("file1.yml", "file2.yml");
    }

    @Test
    void testYamlStylishFormat() throws Exception {
        assertFormat("file1.yml", "file2.yml", "stylish", "expected_stylish.txt");
    }

    @Test
    void testYamlPlainFormat() throws Exception {
        assertFormat("file1.yml", "file2.yml", "plain", "expected_plain.txt");
    }

    @Test
    void testYamlJsonFormat() throws Exception {
        assertFormat("file1.yml", "file2.yml", "json", "expected_json.txt");
    }

    private void assertDefaultMatchesStylish(String firstInput, String secondInput) throws Exception {
        Path first = getFixturePath(firstInput);
        Path second = getFixturePath(secondInput);

        String expected = getFixtureContent("expected_stylish.txt");
        String defaultOutput = Differ.generate(first.toString(), second.toString());
        String stylishOutput = Differ.generate(first.toString(), second.toString(), "stylish");

        assertEquals(expected, defaultOutput);
        assertEquals(stylishOutput, defaultOutput);
    }

    private void assertFormat(
        String firstInput,
        String secondInput,
        String format,
        String expectedFixture
    ) throws Exception {
        Path first = getFixturePath(firstInput);
        Path second = getFixturePath(secondInput);
        String expected = getFixtureContent(expectedFixture);

        assertEquals(expected, Differ.generate(first.toString(), second.toString(), format));
    }
}
