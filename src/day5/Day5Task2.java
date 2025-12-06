import day5.Input;
import day5.InputReader;
import day5.LongPair;

void main() {
    Path path = Path.of("src\\day5\\input.txt");
    try {
        Input input = InputReader.parseFile(path);
        List<LongPair> sortedSegments =
                input.ranges()
                        .stream()
                        .sorted(
                                Comparator
                                        .comparing(LongPair::value)
                                        .thenComparing(LongPair::pos))
                        .toList();

        long res = 0;
        int depth = 0;
        long lastStart = 0;

        for (var pair: sortedSegments) {
            if (pair.pos() == 1) {
                if (depth == 0) {
                    lastStart = pair.value();
                }
                depth += 1;
            } else {
                depth -= 1;
                if (depth == 0) {
                    res += (pair.value() - lastStart + 1);
                }
            }
        }

        System.out.println(res);
    } catch (IOException e) {
        System.out.println("An error occurred: " + e.getMessage());
    }
}