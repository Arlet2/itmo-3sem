import lombok.Data;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;

@ManagedBean
@SessionScoped
@Data
public class Test {

    private String text = "Meow";

    public void baba() {
        System.out.println(text);
    }
}
