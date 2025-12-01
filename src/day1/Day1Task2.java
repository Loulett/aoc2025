import day1.InputReader;

import static java.lang.Math.abs;

void main() {
    Path path = Path.of("src\\day1\\input.txt");
    try {
        int result = 0;
        int numberOnDial = 50;
        for (Integer i : InputReader.parseFile(path)) {
            int numPassedZero = abs(i / 100);
            result += numPassedZero;

            int newAbsNumberOnDial = numberOnDial + (i % 100);
            if (newAbsNumberOnDial % 100 == 0) {
                result += 1;
            }
            else if (numberOnDial * newAbsNumberOnDial < 0) {
                result += 1;
            }
            else if (abs(newAbsNumberOnDial) > 100) {
                result += 1;
            }
            numberOnDial = newAbsNumberOnDial % 100;
        }
        System.out.println(result);
    } catch (IOException e) {
        System.out.println("An error occurred: " + e.getMessage());
    }
}