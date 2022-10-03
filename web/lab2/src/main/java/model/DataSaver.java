package model;

import data.Row;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DataSaver {

    public static final String SAVING_ATTRIBUTE_NAME = "rows";

    public void saveData(HttpSession session, Row row) {
        List<Row> rows;
        try {
            rows = (List<Row>) session.getAttribute(SAVING_ATTRIBUTE_NAME);
        } catch (ClassCastException e) {
            rows = new ArrayList<>();
        }

        if (Optional.ofNullable(rows).isEmpty())
            rows = new ArrayList<>();

        rows.add(row);

        session.setAttribute(SAVING_ATTRIBUTE_NAME, rows);
    }
}
