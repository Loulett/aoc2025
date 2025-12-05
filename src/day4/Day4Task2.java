import day4.InputReader;
import day4.Table;

public record TableWithRes(Table table, int res) {}

void main() {
    Path path = Path.of("src\\day4\\input.txt");
    try {
        List<List<Integer>> input = InputReader.parseFile(path);
        int lenX = input.size();
        int lenY = input.getFirst().size();
        Table table = new Table(input);

        int res = 0;
        TableWithRes tableWithRes = makeStep(table, lenX, lenY);

        while (tableWithRes.res > 0) {
            res += tableWithRes.res;
            table.multiply(tableWithRes.table);
            tableWithRes = makeStep(table, lenX, lenY);
        }

        System.out.println(res);
    } catch (IOException e) {
        System.out.println("An error occurred: " + e.getMessage());
    }
}

TableWithRes makeStep(Table inputTable, int lenX, int lenY) {
    Table forkliftsToRemove = Table.ones(lenX, lenY);

    int stepCount = 0;

    for (int i = 0; i < lenX; i++) {
        for (int j = 0; j < lenY; j++) {
            if (inputTable.get(i, j) == 1 && inputTable.getNeighboursSum(i, j) < 4) {
                forkliftsToRemove.set(i, j, 0);
                stepCount += 1;
            }
        }
    }

    return new TableWithRes(forkliftsToRemove, stepCount);
}