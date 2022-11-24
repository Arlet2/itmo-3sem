package beans;

import database.Ant;
import database.IncorrectTypeException;
import database.Point;
import database.Spider;
import tableHandlers.HitChecker;
import lombok.Data;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;
import java.text.DecimalFormat;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

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

        Point point = createRow()
                .orElseThrow(() -> {
                    throw new IncorrectTypeException();
                });

        long endTime = System.nanoTime();

        point.setScriptTime(new DecimalFormat("#0.00").format((endTime - startTime) * Math.pow(10, -6)));

        if (coordinatesBean.getType().equals("spider"))
            pointsBean.addSpider((Spider) point);
        else if (coordinatesBean.getType().equals("ant"))
            pointsBean.addAnt((Ant) point);
    }

    private Optional<Point> createRow() {
        Point point;
        if (coordinatesBean.getType().equals("spider")) {
            point = new Spider();
            ((Spider) point).setLegCount(coordinatesBean.getLegCount());
        } else if (coordinatesBean.getType().equals("ant")) {
            point = new Ant();
            ((Ant) point).setMustacheLength(coordinatesBean.getMustacheLength());
        } else
            return Optional.empty();

        point.setDate(
                ZonedDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss - VV O"))
        );

        point.setX(coordinatesBean.getCoordinateX());
        point.setY(coordinatesBean.getCoordinateY());
        point.setR(coordinatesBean.getRadius());

        point.setHit(hitChecker.isHit(point.getX(), point.getY(), point.getR()));

        return Optional.of(point);
    }
}
