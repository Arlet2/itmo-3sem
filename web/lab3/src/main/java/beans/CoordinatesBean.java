package beans;

import lombok.Data;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;

@Data
@ManagedBean (name = "coordinates")
@SessionScoped
public class CoordinatesBean implements Serializable {
    private int coordinateX=0;
    private float coordinateY=0;
    private float radius=1;

    public CoordinatesBean()
    {

    }
}
