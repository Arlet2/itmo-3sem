package com.arlet.lab4.services;

import org.springframework.stereotype.Service;

@Service
public class HitCheckService {

    public boolean checkCoordinates(int x, float y, int r) {
        if (x == 0 || y == 0)
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

    private boolean checkAxes(int x, float y, int r) {
        return -r <= x && x <= r / 2 && -r / 2f <= y && y <= r;
    }

    private boolean checkFirstQuarter(int x, float y, int r) {
        return 0 < x && x <= r / 2 && 0 < y && y <= r;
    }

    private boolean checkSecondQuarter(int x, float y, int r) {
        return y <= r / 2f + -x / 2f;
    }

    private boolean checkThirdQuarter(int x, float y, int r) {
        return false;
    }

    private boolean checkFourthQuarter(int x, float y, int r) {
        return r * r / 4f >= x * x + y * y;
    }
}
