package model;

import data.Row;

import java.util.List;
import java.util.Optional;

import static java.lang.System.out;

public class RowsPrinting {
    private RowsPrinting() {

    }

    public static void printRows(List<Row> rows) {
        if (Optional.ofNullable(rows).isPresent()) {
            for (Row row : rows)
                out.println(row.toString());
        }
    }
}
