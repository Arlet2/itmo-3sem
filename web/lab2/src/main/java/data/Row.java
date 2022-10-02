package data;

import java.io.Serializable;

public class Row implements Serializable {
    private String x;
    private String y;
    private String r;

    private String hitStatus;

    public Row() {

    }

    public Row(String x, String y, String r, String hitStatus) {
        this.x = x;
        this.y = y;
        this.r = r;
        this.hitStatus = hitStatus;
    }

    public void setX(String x) {
        this.x = x;
    }

    public void setY(String y) {
        this.y = y;
    }

    public void setR(String r) {
        this.r = r;
    }

    public void setHitStatus(String hitStatus) {
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

    @Override
    public String toString() {
        return "<tr>" +
                "<td>X: "+x+"\nY: "+y+"\nR: "+r+"</td>"+
                "<td>"+hitStatus+"</td>"+
                "</tr>";
    }
}
