package day4;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

public class InputReader {
    public static List<List<Integer>> parseFile(Path path) throws IOException {
        try (var lineStream = Files.lines(path)) {
            return lineStream
                    .map(String::trim)
                    .filter(s -> !s.isEmpty())
                    .map(InputReader::parseInput)
                    .collect(Collectors.toList());
        }
    }

    private static List<Integer> parseInput(String s) {
        return s.chars().mapToObj(c -> (char) c).map(InputReader::getValue).collect(Collectors.toList());
    }

    private static Integer getValue(char letter) {
        return switch (letter) {
            case '.' -> 0;
            case '@' -> 1;
            default -> throw new IllegalArgumentException("The input letter can be only . or @");
        };
    }
}
