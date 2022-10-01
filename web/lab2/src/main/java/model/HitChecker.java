package model;

public class HitChecker {

    private float x;
    private float y;
    private int r;

    public boolean isHit(float x, float y, int r) {
        this.x = x;
        this.y = y;
        this.r = r;
        if (x == 0 || y == 0)
            return checkAxes();
        else if (x > 0 && y > 0)
            return checkFirstQuarter();
        else if (x < 0 && y > 0)
            return checkSecondQuarter();
        else if (x < 0 && y < 0)
            return checkThirdQuarter();
        else
            return checkFourthQuarter();
    }

    private boolean checkAxes() {
        return -r <= x && x <= r &&
                -(r / 2f) <= y && y <= r;
    }

    private boolean checkFirstQuarter() {
        return x * x + y * y <= r * r;
    }

    private boolean checkSecondQuarter() {
        return false;
    }

    private boolean checkThirdQuarter() {
        return -r <= x && y >= -(r / 2f);
    }

    private boolean checkFourthQuarter() {
        return (x / 2) - (r / 2f) >= y;
    }
}
