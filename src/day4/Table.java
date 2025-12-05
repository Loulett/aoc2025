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

        List<Integer> paddingRow = Collections.nCopies(paddedWidth, 0);
        Stream<List<Integer>> paddedRowsStream = input.stream()
                .map(row -> {
                    // Create a mutable copy of the row, add 0 to start and end
                    List<Integer> newRow = new ArrayList<>(row.size() + 2);
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

    private final List<List<Integer>> table;
}
