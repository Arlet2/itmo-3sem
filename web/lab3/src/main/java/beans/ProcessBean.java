package beans;

import tableHandlers.HitChecker;
import tableHandlers.Row;
import lombok.Data;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@Data
@ManagedBean(name="process")
@SessionScoped
public class ProcessBean implements Serializable {

    @ManagedProperty(value="#{coordinates}")
    private CoordinatesBean coordinatesBean;

    @ManagedProperty(value="#{database}")
    private DatabaseBean databaseBean;

    private HitChecker hitChecker = new HitChecker();

    public ProcessBean() {

    }

    public void processNewValue() {
        long startTime = System.nanoTime();

        Row row = createRow();

        long endTime = System.nanoTime();

        row.setScriptTime((long)((endTime - startTime) * Math.pow(10, -6)));

        System.out.println(row);
    }

    private Row createRow() {
        Row row = new Row();
        row.setDate(
                ZonedDateTime.now()
                        .format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss - VV O"))
        );

        row.setX(coordinatesBean.getCoordinateX());
        row.setY(coordinatesBean.getCoordinateY());
        row.setR(coordinatesBean.getRadius());

        row.setHit(hitChecker.isHit(row.getX(), row.getY(), row.getR()));

        return row;
    }
}
