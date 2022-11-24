package beans;

import database.AbstractPointDAO;
import database.PointDAO;
import lombok.Data;
import database.Point;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import java.io.Serializable;
import java.util.List;

@Data
@ManagedBean(name = "points", eager = true)
@ApplicationScoped
public class PointDAOBean implements Serializable {
    private AbstractPointDAO pointDAO;
    private List<Point> pointsCollection;

    public PointDAOBean() {
        pointDAO = new PointDAO();
        updatePointsCollection();
    }

    public void updatePointsCollection() {
        pointsCollection = pointDAO.getPoints();
    }

    public void addPoint(Point point) {
        pointDAO.addPoint(point);

        updatePointsCollection();
    }

}
