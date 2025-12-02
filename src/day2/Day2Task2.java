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
    for (int i = 1; i <= numLength / 2; i++) {
        if (numLength % i != 0) {
            continue;
        }
        if (isInvalidForLength(num, i)) {
            return true;
        }
    }
    return false;
}

boolean isInvalidForLength(long num, int blockLen) {
    int decBlockLen = Math.powExact(10, blockLen);
    long block = num % decBlockLen;
    while (num > 0) {
        if (num % decBlockLen != block) {
            return false;
        }
        num /= decBlockLen;
    }
    return true;
}

int getLength(long num) {
    int length = 0;
    while (num > 0) {
        length++;
        num /= 10;
    }
    return length;
}