package model;

import data.Row;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class RowsCreator {

    private final CoordinatesValidator validator;
    private final HitChecker hitChecker;

    public RowsCreator(CoordinatesValidator validator, HitChecker hitChecker) {
        this.validator = validator;
        this.hitChecker = hitChecker;
    }

    public Row createRow(String x, String y, String r) {
        long startTime = System.nanoTime();
        String result;
        String time = ZonedDateTime.now()
                .format(DateTimeFormatter
                        .ofPattern("dd.MM.yyyy HH:mm:ss - VV O"));
        if (!validator.isCoordinatesHaveCorrectTypes(x, y, r) && !validator.isCoordinatesCorrect(x, y, r)) {
            result = "Incorrect data";
        } else {
            if (hitChecker.isHit(x, y, r))
                result = "hit!";
            else
                result = "no hit...";
        }

        long endTime = System.nanoTime();

        return new Row(time, x, y, r, result,
                new DecimalFormat("#0.00")
                        .format((endTime-startTime)*Math.pow(10, -6))+"");
    }
}
