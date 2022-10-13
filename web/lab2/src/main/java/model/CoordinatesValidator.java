package model;

public class CoordinatesValidator {
    private float minX;
    private float maxX;
    private float minY;
    private float maxY;
    private int minR;
    private int maxR;

    public boolean isCoordinatesHaveCorrectTypes(String x, String y, String r) {
        try {
            tryToCastXToType(x);
            tryToCastYToType(y);
            tryToCastRToType(r);
        } catch (NumberFormatException | NullPointerException e) {
            return false;
        }
        return true;
    }

    private void tryToCastXToType(String x) {
        Float.parseFloat(x);
    }

    private void tryToCastYToType(String y) {
        Float.parseFloat(y);
    }
    private void tryToCastRToType(String r) {
        Integer.parseInt(r);
    }

    public boolean isCoordinatesCorrect(String textX, String textY, String textR) {
        float x = Float.parseFloat(textX);
        float y = Float.parseFloat(textY);
        int r = Integer.parseInt(textR);

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
