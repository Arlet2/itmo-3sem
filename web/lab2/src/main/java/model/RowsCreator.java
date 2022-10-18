package model;

import data.Row;

import java.text.DecimalFormat;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class RowsCreator {

    private final CoordinatesValidator validator;
    private final HitChecker hitChecker;

    public RowsCreator(CoordinatesValidator validator, HitChecker hitChecker) {
        this.validator = validator;
        this.hitChecker = hitChecker;
    }

    public Row createRow(Coordinates coordinates) {
        long startTime = System.nanoTime();
        String result;
        String time = ZonedDateTime.now()
                .format(DateTimeFormatter
                        .ofPattern("dd.MM.yyyy HH:mm:ss - VV O"));

        if (hitChecker.isHit(coordinates.getX(), coordinates.getY(), coordinates.getR()))
            result = "hit!";
        else
            result = "no hit...";

        long endTime = System.nanoTime();

        return new Row(time, coordinates.getX()+"", coordinates.getY()+"", coordinates.getR()+"", result,
                new DecimalFormat("#0.00")
                        .format((endTime - startTime) * Math.pow(10, -6)) + "");
    }

    public Row createEmptyRow(String x, String y, String r) {
        String time = ZonedDateTime.now()
                .format(DateTimeFormatter
                        .ofPattern("dd.MM.yyyy HH:mm:ss - VV O"));
        return new Row(time, x, y, r, "Incorrect data", "0");
    }
}
