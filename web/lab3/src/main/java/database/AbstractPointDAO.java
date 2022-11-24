package database;

import java.util.List;

public interface AbstractPointDAO {
    void addSpider(Spider spider);

    void addAnt(Ant ant);

    List<Point> getPoints();

    void removeAllPoints();
}
