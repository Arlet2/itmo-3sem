package beans;

import lombok.Data;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import java.io.Serializable;

@Data
@ManagedBean(name="database")
@ApplicationScoped
public class DatabaseBean implements Serializable {
    public DatabaseBean() {

    }

    public void test() {
        
    }
}
