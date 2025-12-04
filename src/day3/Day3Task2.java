import day3.InputReader;
import day3.IntWithPos;

void main() {
    Path path = Path.of("src\\day3\\input.txt");
    try {
        List<List<Integer>> batteries = InputReader.parseFile(path);
        Long result = batteries
                .stream()
                .map(this::getBiggestVoltage)
                .mapToLong(Long::longValue)
                .sum();
        System.out.println(result);
    } catch (IOException e) {
        System.out.println("An error occurred: " + e.getMessage());
    }
}

Long getBiggestVoltage(List<Integer> battery) {
    List<Integer> resInt = getBiggestVoltageInternal(battery, 0, battery.size(), 12);

    long res = 0L;
    for (Integer integer : resInt) {
        res = res * 10 + integer;
    }
    return res;
}

List<Integer> getBiggestVoltageInternal(List<Integer> battery, int start, int end, int numLeft) {
    if (numLeft == 0 || start == end || end - start < numLeft) {
        return List.of();
    }
    if (end - start == 1 && numLeft == 1) {
        return List.of(battery.get(start));
    }
    IntWithPos firstHighestNum = getHighestIntWithPos(battery, start, end);
    if (end - firstHighestNum.position() >= numLeft) {
        return Stream.concat(
                        Stream.of(firstHighestNum.value()),
                        getBiggestVoltageInternal(
                                battery,
                                firstHighestNum.position() + 1,
                                end,
                                numLeft - 1).stream())
                .collect(Collectors.toList());
    } else {
        return Stream.concat(
                Stream.concat(
                        getBiggestVoltageInternal(
                                battery,
                                start,
                                firstHighestNum.position(),
                                numLeft - (end - firstHighestNum.position())).stream(),
                        Stream.of(firstHighestNum.value())),
                getBiggestVoltageInternal(
                        battery,
                        firstHighestNum.position() + 1,
                        end,
                        end - firstHighestNum.position() - 1).stream()
        ).collect(Collectors.toList());
    }
}

IntWithPos getHighestIntWithPos(List<Integer> array, int start, int end) {
    int intValue = 0;
    int intPosition = 0;
    for (int i = start; i < end; i++) {
        if (array.get(i) > intValue) {
            intValue = array.get(i);
            intPosition = i;
        }
    }
    return new IntWithPos(intValue, intPosition);
}


