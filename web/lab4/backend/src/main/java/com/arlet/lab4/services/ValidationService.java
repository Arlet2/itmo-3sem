package com.arlet.lab4.services;

import org.springframework.stereotype.Service;

@Service
public class ValidationService {
    private final int minX;
    private final int maxX;

    private final float minY;
    private final float maxY;

    private final int minR;
    private final int maxR;

    public ValidationService() {
        minX = -5;
        maxX = 3;

        minY = -5;
        maxY = 5;

        minR = -5;
        maxR = 3;
    }

    public boolean validateInputData(int x, float y, int r) {
        return isXCorrect(x) && isYCorrect(y) && isRCorrect(r);
    }

    private boolean isXCorrect(int x) {
        return minX <= x && x <= maxX;
    }

    private boolean isYCorrect(float y) {
        return minY <= y && y <= maxY;
    }

    private boolean isRCorrect(int r) {
        return minR <= r && r <= maxR && r > 0;
    }
}
