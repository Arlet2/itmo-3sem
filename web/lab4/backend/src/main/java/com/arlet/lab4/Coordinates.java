package com.arlet.lab4;

import lombok.Data;

@Data
public class Coordinates {
    private final int x;
    private final float y;
    private final int r;

    public Coordinates(int x, float y, int r) {
        this.x = x;
        this.y = y;
        this.r = r;
    }
}
