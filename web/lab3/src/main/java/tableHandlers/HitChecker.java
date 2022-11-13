package beans;

public class HitChecker {
    public boolean isHit(int x, float y, float r) {
        if (x == 0 && y == 0)
            return checkAxes(x, y, r);
        if (x > 0 && y > 0)
            return checkFirstQuarter(x, y, r);
        if (x < 0 && y > 0)
            return checkSecondQuarter(x, y, r);
        if (x < 0 && y < 0)
            return checkThirdQuarter(x, y, r);
        if (x > 0 && y < 0)
            return checkFourthQuarter(x, y, r);
        return false;
    }

    private boolean checkAxes(int x, float y, float r) {
        return -r <= x && x<= r && -r/2 <= y && y <= r;
    }

    private boolean checkFirstQuarter(int x, float y, float r) {
        return x <= r && y <= r;
    }

    private boolean checkSecondQuarter(int x, float y, float r) {
        return y <= x + r;
    }

    private boolean checkThirdQuarter(int x, float y, float r) {
        return r*r/4 <= x*x + y*y;
    }

    private boolean checkFourthQuarter(int x, float y, float r) {
        return false;
    }
}
