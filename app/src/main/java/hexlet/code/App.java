package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.util.concurrent.Callable;

@Command(
        name = "gendiff",
        description = "Compares two configuration files and shows a difference.",
        mixinStandardHelpOptions = true,
        version = "gendiff 1.0"
)
public final class App implements Callable<Integer> {
    @Parameters(index = "0", description = "path to first file", paramLabel = "filepath1")
    private String filePath1;

    @Parameters(index = "1", description = "path to second file", paramLabel = "filepath2")
    private String filePath2;

    @Option(names = {"-f", "--format"}, description = "output format", defaultValue = "stylish")
    private String format;

    @Override
    public Integer call() throws Exception {
        String diff = Differ.generate(filePath1, filePath2, format);
        System.out.println(diff);
        return 0;
    }

    public static void main(String[] args) {
        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
    }
}
