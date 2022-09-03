<?php
function isHit($x, $y, $r) : bool
{
    if ($x == 0 || $y == 0) {
        return checkAxes($x, $y, $r);
    } else if ($x > 0 && $y > 0) {
        return isFirstQuarter($x, $y, $r);
    } else if ($x < 0 && $y > 0) {
        return isSecondQuarter($x, $y, $r);
    } else if ($x < 0 && $y < 0) {
        return isThirdQuarter($x, $y, $r);
    } else {
        return isFourthQuarter($x, $y, $r);
    }
}

function checkAxes($x, $y, $r) : bool
{
    return $x <= $r && $x >= -$r && $y <= $r && $y >= -$r;
}

function isFirstQuarter($x, $y, $r) : bool
{
    return $x ** 2 + $y ** 2 <= $r ** 2;
}

function isSecondQuarter($x, $y, $r) : bool
{
    return $y <= $x + $r / 2;
}

function isThirdQuarter($x, $y, $r) : bool
{
    return abs(+$y) <= $r && abs(+$x) <= $r;
}

function isFourthQuarter($x, $y, $r) : bool
{
    return false;
}
