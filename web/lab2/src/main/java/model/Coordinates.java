package model;

public class Coordinates {
    private final float x;
    private final float y;
    private final int r;

    Coordinates(float x, float y, int r) {
        this.x = x;
        this.y = y;
        this.r = r;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public int getR() {
        return r;
    }
}
