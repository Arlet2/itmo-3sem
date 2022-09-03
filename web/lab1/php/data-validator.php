<?php

require_once "utils.php";

function preprocessValue($value)
{
    return clearSpaces(changeCommaToPoint($value));
}

function isArgumentsAreNumbers($x, $y, $r): bool
{
    return is_numeric($x) && is_numeric($y) && is_numeric($r);
}

function checkX($x): bool
{
    return !is_float($x) && $x >= -5 && $x <= 3;
}

function checkY($y): bool
{
    return $y >= -5 && $y <= 5;
}

function checkR($r): bool
{
    return (!is_float($r) && $r >= 0) && $r >= 1 && $r <= 5;
}
