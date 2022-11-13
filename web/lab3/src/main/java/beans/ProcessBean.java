import beans.CoordinatesBean;
import beans.HitChecker;
import beans.Row;
import lombok.Data;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;

@Data
@ManagedBean(name="process")
@SessionScoped
public class ProcessBean implements Serializable {

    @ManagedProperty(value="#{coordinates}")
    private CoordinatesBean coordinatesBean;

    private HitChecker hitChecker = new HitChecker();

    public ProcessBean() {

    }

    public void processNewValue() {
        System.out.println(coordinatesBean.getCoordinateX());
        /*
        long startTime = System.nanoTime();

        Row row = createRow();

        long endTime = System.nanoTime();

        row.setScriptTime((long)((endTime - startTime) * Math.pow(10, -6)));

        System.out.println(row);

         */
    }

    private Row createRow() {
        /*
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

         */
        return null;
    }
}
