import day5.Input;
import day5.InputReader;
import day5.IntPair;

void main() {
    Path path = Path.of("src\\day5\\input.txt");
    try {
        Input input = InputReader.parseFile(path);
        List<IntPair> sortedSegments =
                input.ranges()
                        .stream()
                        .sorted(
                                Comparator
                                        .comparing(IntPair::value)
                                        .thenComparing(IntPair::pos))
                        .toList();

        int freshCount = 0;

        for (var good: input.goods()) {
            int depth = 0;

            for (var pair: sortedSegments) {
                if (good < pair.value() || (good == pair.value() && pair.pos() == 2)) {
                    if (depth > 0) {
                        freshCount += 1;
                    }
                    break;
                }
                if (pair.pos() == 1) {
                    depth += 1;
                } else {
                    depth -= 1;
                }
            }
        }

        System.out.println(freshCount);
    } catch (IOException e) {
        System.out.println("An error occurred: " + e.getMessage());
    }
}