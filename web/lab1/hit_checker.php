<?php
function isHit($x, $y, $r)
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

function checkAxes($x, $y, $r)
{
    return $x <= $r && $x >= -$r && $y <= $r && $y >= -$r;
}

function isFirstQuarter($x, $y, $r)
{
    return pow($x, 2) + pow($y, 2) <= pow($r, 2);
}

function isSecondQuarter($x, $y, $r)
{
    return $y <= $x + $r / 2;
}

function isThirdQuarter($x, $y, $r)
{
    return abs(+$y) <= $r && abs(+$x) <= $r;
}

function isFourthQuarter($x, $y, $r)
{
    return false;
}
