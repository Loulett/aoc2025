import day1.InputReader;

void main() {
    Path path = Path.of("src\\day1\\input.txt");
    try {
        int result = 0;
        int numberOnDial = 50;
        for (Integer i : InputReader.parseFile(path)) {
            numberOnDial = (numberOnDial + i) % 100;
            if (numberOnDial == 0) {
                result += 1;
            }
        }
        System.out.println(result);
    } catch (IOException e) {
        System.out.println("An error occurred: " + e.getMessage());
    }
}