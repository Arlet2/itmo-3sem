package beans;

import database.*;
import lombok.Data;

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

    public void addSpider(Spider spider) {
        pointDAO.addSpider(spider);

        updatePointsCollection();
    }

    public void addAnt(Ant ant) {
        pointDAO.addAnt(ant);

        updatePointsCollection();
    }

}
