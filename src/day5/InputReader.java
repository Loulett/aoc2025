package day5;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class InputReader {
    public static Input parseFile(Path path) throws IOException {
        List<LongPair> pairs = new ArrayList<>();
        List<Long> goods = new ArrayList<>();
        boolean finishedPairs = false;
        for (var line: Files.readAllLines(path)) {
            if (line.isBlank()) {
                finishedPairs = true;
                continue;
            }
            if (!finishedPairs) {
                var substrings = line.split("-", 2);
                pairs.add(new LongPair(Long.parseLong(substrings[0]), 1));
                pairs.add(new LongPair(Long.parseLong(substrings[1]), 2));
            } else {
                goods.add(Long.parseLong(line));
            }
        }
        return new Input(pairs, goods);
    }
}
