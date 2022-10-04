package data;

import java.io.Serializable;

public class Row implements Serializable {
    private String time;
    private String x;
    private String y;
    private String r;

    private String hitStatus;

    private String executionTime;

    public Row() {

    }

    public Row(String time, String x, String y, String r, String hitStatus, String executionTime) {
        this.time = time;
        this.x = x;
        this.y = y;
        this.r = r;
        this.hitStatus = hitStatus;
        this.executionTime = executionTime;
    }

    public void setTime(String time) {
        this.time = time;
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

    public String getTime() {
        return time;
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
        return "<tr class=\"dataRow\">" +
                "<td class=\"dataRow\">"+time+"</td>" +
                "<td class=\"dataRow\">X: "+x+"\nY: "+y+"\nR: "+r+"</td>"+
                "<td class=\"dataRow\">"+hitStatus+"</td>"+
                "<td class=\"dataRow\">"+(executionTime)+" ms</td>"+
                "</tr>";
    }
}
