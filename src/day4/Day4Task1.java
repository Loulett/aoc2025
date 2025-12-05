import day4.InputReader;
import day4.Table;

void main() {
    Path path = Path.of("src\\day4\\input.txt");
    try {
        List<List<Integer>> input = InputReader.parseFile(path);
        Table table = new Table(input);

        int res = 0;

        for (int i = 0; i < input.size(); i++) {
            for (int j = 0; j < input.getFirst().size(); j++) {
                if (table.get(i, j) == 1 && table.getNeighboursSum(i, j) < 4) {
                    res += 1;
                }
            }
        }

        System.out.println(res);
    } catch (IOException e) {
        System.out.println("An error occurred: " + e.getMessage());
    }
}