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
    private String dateTime = generateTime();
    private String timeSignal;
    private String time;

    public ClockBean() {
        update();
    }

    public void update() {
        long timeSeconds = System.currentTimeMillis() / 1000;

        updateTimeSignal(timeSeconds);
        updateTime(timeSeconds);

        time = timeSignal + " " + dateTime + " " +timeSignal;
    }

    private void updateTime(long timeSeconds) {
        if (timeSeconds % CLOCK_TICK_INTERVAL == 0)
            dateTime = generateTime();
    }

    private void updateTimeSignal(long timeSeconds) {
        if (timeSeconds % 4 == 0)
            timeSignal = "-";
        else if (timeSeconds % 4 == 1)
            timeSignal = "/";
        else if (timeSeconds % 4 == 2)
            timeSignal = "|";
        else
            timeSignal = "\\";
    }

    private String generateTime() {
        return ZonedDateTime.now()
                .format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss - VV O"));
    }

    public String getTime() {
        update();
        return time;
    }
}
