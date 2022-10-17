package model;

import data.Row;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DataSaver {

    public static final int MAX_ROWS = 10;
    private static final String SAVING_ATTRIBUTE_NAME = "rows";

    private DataSaver() {

    }

    public static synchronized void saveData(HttpSession session, Row row) {
        List<Row> rows;
        try {
            rows = (List<Row>) session.getAttribute(SAVING_ATTRIBUTE_NAME);
        } catch (ClassCastException e) {
            rows = new ArrayList<>();
        }

        if (Optional.ofNullable(rows).isEmpty())
            rows = new ArrayList<>();

        if (rows.size() >= MAX_ROWS)
            rows.remove(MAX_ROWS - 1);
        rows.add(0, row);

        session.setAttribute(SAVING_ATTRIBUTE_NAME, rows);
    }

    public static synchronized List<Row> loadData(HttpSession session) {
        return (List<Row>) session.getAttribute(SAVING_ATTRIBUTE_NAME);
    }
}
