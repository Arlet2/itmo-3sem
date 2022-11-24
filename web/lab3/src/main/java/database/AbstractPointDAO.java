package database;

import java.util.List;

public interface AbstractPointDAO {
    void addPoint(Point point);

    List<Point> getPoints();

    void removeAllPoints();
}
