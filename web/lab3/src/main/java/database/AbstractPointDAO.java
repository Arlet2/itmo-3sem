package database;

import java.util.List;

public interface AbstractPointDAO {
    void addPoint(Row row);

    List<Row> getPoints();

    void removeAllPoints();
}
