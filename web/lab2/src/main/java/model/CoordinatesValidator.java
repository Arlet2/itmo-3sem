package model;

public class CoordinatesValidator {
    private float minX;
    private float maxX;
    private float minY;
    private float maxY;
    private int minR;
    private int maxR;

    public boolean isCoordinatesHaveCorrectTypes(Object x, Object y, Object r) {
        try {
            tryToCastXToType(x);
            tryToCastYToType(y);
            tryToCastRToType(r);
        } catch (NumberFormatException | NullPointerException e) {
            return false;
        }
        return true;
    }

    private void tryToCastXToType(Object x) {
        Float.parseFloat((String)x);
    }

    private void tryToCastYToType(Object y) {
        Float.parseFloat((String)y);
    }
    private void tryToCastRToType(Object r) {
        Integer.parseInt((String)r);
    }

    public boolean isCoordinatesCorrect(float x, float y, int r) {
        return isXCorrect(x) && isYCorrect(y) && isRCorrect(r);
    }

    private boolean isXCorrect(float x) {
        return minX <= x && x <= maxX;
    }

    private boolean isYCorrect(float y) {
        return minY <= y && y <= maxY;
    }

    private boolean isRCorrect(int r) {
        return minR <= r && r <= maxR;
    }

    public CoordinatesValidator setMinX(float minX) {
        this.minX = minX;
        return this;
    }

    public CoordinatesValidator setMaxX(float maxX) {
        this.maxX = maxX;
        return this;
    }

    public CoordinatesValidator setMinY(float minY) {
        this.minY = minY;
        return this;
    }

    public CoordinatesValidator setMaxY(float maxY) {
        this.maxY = maxY;
        return this;
    }

    public CoordinatesValidator setMinR(int minR) {
        this.minR = minR;
        return this;
    }

    public CoordinatesValidator setMaxR(int maxR) {
        this.maxR = maxR;
        return this;
    }
}
