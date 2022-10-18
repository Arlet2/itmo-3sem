package model;

import java.util.Optional;

public class CoordinatesValidator {
    private float minX;
    private float maxX;
    private float minY;
    private float maxY;
    private int minR;
    private int maxR;

    public Optional<Coordinates> convertTextToCoordinates(String textX, String textY, String textR) {
        float x;
        float y;
        int r;
        try {
            x = Float.parseFloat(textX);
            y = Float.parseFloat(textY);
            r = Integer.parseInt(textR);
        } catch (NumberFormatException e) {
            return Optional.empty();
        }

        if(isXCorrect(x) && isYCorrect(y) && isRCorrect(r))
            return Optional.ofNullable(new Coordinates(x, y, r));
        return Optional.empty();
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
