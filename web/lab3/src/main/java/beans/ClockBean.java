package beans;

import lombok.Data;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@Data
@ManagedBean(name = "clock")
@SessionScoped
public class ClockBean implements Serializable {
    private final int CLOCK_TICK_INTERVAL = 12;
    private String time = generateTime();
    private String timeSignal;

    public ClockBean() {
        updateTime();
    }

    public void updateTime() {
        long timeSeconds = System.currentTimeMillis() / 1000;
        if (timeSeconds % 4 == 0)
            timeSignal = "-";
        else if (timeSeconds % 4 == 1)
            timeSignal = "/";
        else if (timeSeconds % 4 == 2)
            timeSignal = "|";
        else
            timeSignal = "\\";
        if (timeSeconds % CLOCK_TICK_INTERVAL == 0)
            time = generateTime();
    }

    private String generateTime() {
        return ZonedDateTime.now()
                .format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss - VV O"));
    }
}
