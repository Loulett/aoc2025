import day2.InputReader;
import day2.LongPair;

void main() {
    Path path = Path.of("src\\day2\\input.txt");
    try {
        List<LongPair> pairs = InputReader.parseFile(path);
        long result = pairs
                .stream()
                .flatMapToLong(longPair -> LongStream.rangeClosed(longPair.first(), longPair.second()))
                .filter(this::isInvalid)
                .sum();
        System.out.println(result);
    } catch (IOException e) {
        System.out.println("An error occurred: " + e.getMessage());
    }
}

boolean isInvalid(long num) {
    int numLength = getLength(num);
    if (numLength % 2 == 1) {
        return false;
    }
    int dec = Math.powExact(10, (numLength / 2));
    return num / dec == num % dec;
}

int getLength(long num) {
    int length = 0;
    while (num > 0) {
        length++;
        num /= 10;
    }
    return length;
}