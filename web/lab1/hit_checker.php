<?php
function isHit(int $x, int $y, int $r) : bool
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

function checkAxes(int $x, int $y, int $r) : bool
{
    return $x <= $r && $x >= -$r && $y <= $r && $y >= -$r;
}

function isFirstQuarter(int $x, int $y, int $r) : bool
{
    return $x ** 2 + $y ** 2 <= $r ** 2;
}

function isSecondQuarter(int $x, int $y, int $r) : bool
{
    return $y <= $x + $r / 2;
}

function isThirdQuarter(int $x, int $y, int $r) : bool
{
    return abs(+$y) <= $r && abs(+$x) <= $r;
}

function isFourthQuarter(int $x, int $y, int $r) : bool
{
    return false;
}
