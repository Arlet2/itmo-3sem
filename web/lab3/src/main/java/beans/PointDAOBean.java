package beans;

import database.AbstractPointDAO;
import database.PointDAO;
import lombok.Data;
import database.Row;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import java.io.Serializable;
import java.util.List;

@Data
@ManagedBean(name="points", eager = true)
@ApplicationScoped
public class PointDAOBean implements Serializable {
    private AbstractPointDAO pointDAO = new PointDAO();
    private List<Row> pointsCollection;

    public PointDAOBean() {
        updatePointsCollection();
    }

    public void updatePointsCollection() {
        pointsCollection = pointDAO.getPoints();
    }

    public void addPoint(Row row) {
        pointDAO.addPoint(row);

        updatePointsCollection();
    }

}
