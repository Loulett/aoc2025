package day2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public final class InputReader {
    public static List<LongPair> parseFile(Path path) throws IOException {
        return Files.readAllLines(path)
                .stream()
                .flatMap(line -> Arrays.stream(line.split(",")))
                .map(InputReader::getPair).collect(Collectors.toList());
    }

    private static LongPair getPair(String line) {
        String[] pair = line.split("-");
        validatePair(pair);
        return new LongPair(Long.parseLong(pair[0]), Long.parseLong(pair[1]));
    }

    private static void validatePair(String[] pair) {
        if (pair.length != 2) {
            throw new IllegalArgumentException(
                    String.format("Expected pair size 2, got %d, pair %s", pair.length, Arrays.toString(pair)));
        }
    }
}
