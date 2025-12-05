package day4;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Table {
    public Table(List<List<Integer>> input) {
        if (input == null || input.isEmpty() || input.getFirst().isEmpty()) {
            // Handle null or empty input gracefully (optional, but good practice)
            table = Collections.emptyList();
            return;
        }

        int columnCount = input.getFirst().size();
        int paddedWidth = columnCount + 2;

        ArrayList<Integer> paddingRow = new ArrayList<>(Collections.nCopies(paddedWidth, 0));
        Stream<ArrayList<Integer>> paddedRowsStream = input.stream()
                .map(row -> {
                    // Create a mutable copy of the row, add 0 to start and end
                    ArrayList<Integer> newRow = new ArrayList<>(row.size() + 2);
                    newRow.add(0);
                    newRow.addAll(row);
                    newRow.add(0);
                    return newRow;
                });

        table = Stream.of(
                        Stream.of(paddingRow),
                        paddedRowsStream,
                        Stream.of(paddingRow)
                )
                .flatMap(s -> s)
                .collect(Collectors.toList());
    }

    public static Table ones(int lenX, int lenY) {
        return new Table(Collections.nCopies(lenX, Collections.nCopies(lenY, 1)));
    }

    public void set(int x, int y, int val) {
        table.get(x + 1).set(y + 1, val);
    }

    public void multiply(Table table1) {
        if (getLength() != table1.getLength() || getWidth() != table1.getWidth()) {
            throw new IllegalArgumentException("tables should be the same size");
        }
        for (int i = 0; i < table.size(); i += 1) {
            for (int j = 0; j < table.getFirst().size(); j += 1) {
                int tableVal = table.get(i).get(j);
                int table1Val = table1.get(i - 1, j - 1);
                table.get(i).set(j, tableVal * table1Val);
            }
        }
    }

    public int getLength() {
        return table.size();
    }
    public int getWidth() {
        if (table.isEmpty()) {
            return 0;
        }
        return table.getFirst().size();
    }
    public Integer get(int x, int y) {
        return table.get(x + 1).get(y + 1);
    }

    public Integer getNeighboursSum(int x, int y) {
        int res = 0;
        for (int i: new int[]{-1, 0, 1}) {
            for (int j: new int[]{-1, 0, 1}) {
                res += get(x+i, y+j);
            }
        }
        res -= get(x, y);
        return res;
    }

    private final List<ArrayList<Integer>> table;
}
