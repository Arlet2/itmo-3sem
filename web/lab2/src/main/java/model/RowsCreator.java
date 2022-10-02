package model;

import data.Row;

public class RowsCreator {

    private final CoordinatesValidator validator;
    private final HitChecker hitChecker;

    public RowsCreator(CoordinatesValidator validator, HitChecker hitChecker) {
        this.validator = validator;
        this.hitChecker = hitChecker;
    }

    public Row createRow(String x, String y, String r) {
        String result;
        if (!validator.isCoordinatesHaveCorrectTypes(x, y, r)) {
            result = "Incorrect data";
        } else {
            if (hitChecker.isHit(x, y, r))
                result = "hit!";
            else
                result = "no hit...";
        }

        return new Row(x, y, r, result);
    }
}
