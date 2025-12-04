import day3.InputReader;
import day3.IntWithPos;

void main() {
    Path path = Path.of("src\\day3\\input.txt");
    try {
        List<List<Integer>> batteries = InputReader.parseFile(path);
        Integer result = batteries
                .stream()
                .map(this::getBiggestVoltage)
                .mapToInt(Integer::intValue)
                .sum();
        System.out.println(result);
    } catch (IOException e) {
        System.out.println("An error occurred: " + e.getMessage());
    }
}

Integer getBiggestVoltage(List<Integer> battery) {
    // get highest digit and its position
    IntWithPos highestNum = getHighestIntWithPos(battery);

    if (highestNum.position() == battery.size() - 1) {
        IntWithPos firstNum = getHighestIntWithPos(battery.subList(0, highestNum.position()));
        return firstNum.value() * 10 + highestNum.value();
    } else {
        IntWithPos secondNum = getHighestIntWithPos(battery.subList(highestNum.position() + 1, battery.size()));
        return highestNum.value() * 10 + secondNum.value();
    }
}

IntWithPos getHighestIntWithPos(List<Integer> array) {
    int intValue = 0;
    int intPosition = 0;
    for (int i = 0; i < array.size(); i++) {
        if (array.get(i) > intValue) {
            intValue = array.get(i);
            intPosition = i;
        }
    }
    return new IntWithPos(intValue, intPosition);
}


