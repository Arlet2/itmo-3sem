package beans;

import lombok.Data;

@Data
public class Row {
    private String date;
    private int x;
    private float y;
    private float r;
    private boolean isHit;
    private long scriptTime;

}
