import lombok.Data;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean (name = "coordinates")
@SessionScoped
@Data
public class CoordinatesBean {
    private int coordinateX;
    private float coordinateY;
    private float radius;

    public CoordinatesBean()
    {

    }
}
