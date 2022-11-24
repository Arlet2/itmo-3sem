package beans;

import database.Point;
import tableHandlers.HitChecker;
import lombok.Data;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;
import java.text.DecimalFormat;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@Data
@ManagedBean(name = "process", eager = true)
@SessionScoped
public class ProcessBean implements Serializable {

    @ManagedProperty(value = "#{coordinates}")
    private CoordinatesBean coordinatesBean;

    @ManagedProperty(value = "#{points}")
    private PointDAOBean pointsBean;

    private HitChecker hitChecker = new HitChecker();

    public ProcessBean() {

    }

    public void processNewValue() {
        System.out.println(coordinatesBean);
        long startTime = System.nanoTime();

        Point point = createRow();

        long endTime = System.nanoTime();

        point.setScriptTime(new DecimalFormat("#0.00").format((endTime - startTime) * Math.pow(10, -6)));

        pointsBean.addPoint(point);
    }

    private Point createRow() {
        Point point = new Point();
        point.setDate(
                ZonedDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss - VV O"))
        );

        point.setX(coordinatesBean.getCoordinateX());
        point.setY(coordinatesBean.getCoordinateY());
        point.setR(coordinatesBean.getRadius());

        point.setHit(hitChecker.isHit(point.getX(), point.getY(), point.getR()));

        return point;
    }
}
