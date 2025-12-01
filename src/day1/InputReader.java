package day1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

public final class InputReader {
    public static List<Integer> parseFile(Path path) throws IOException {
        try (var lineStream = Files.lines(path)) {
            return lineStream
                    .map(String::trim)
                    .filter(s -> !s.isEmpty())
                    .map(InputReader::parseInput)
                    .collect(Collectors.toList());
        }
    }

    private static Integer parseInput(String line) {
        return getMultiplier(line.charAt(0)) * Integer.parseInt(line.substring(1));
    }

    private static Integer getMultiplier(char letter) {
        return switch (letter) {
            case 'R' -> 1;
            case 'L' -> -1;
            default -> throw new IllegalArgumentException("The input letter can be only L or R");
        };
    }
}
