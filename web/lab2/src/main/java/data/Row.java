package data;

public class Row {
    private final String x;
    private final String y;
    private final String r;

    private final String hitStatus;

    public Row(String x, String y, String r, String hitStatus) {
        this.x = x;
        this.y = y;
        this.r = r;
        this.hitStatus = hitStatus;
    }

    public String getX() {
        return x;
    }

    public String getY() {
        return y;
    }

    public String getR() {
        return r;
    }

    public String getHitStatus() {
        return hitStatus;
    }
}
