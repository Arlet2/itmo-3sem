<?php

session_start();

require_once "Timer.php";
require "utils.php";
require "hit-checker.php";
require "data-validator.php";

//$_SERVER['REQUEST_METHOD'] == 'GET')

$timer = new Timer();

$timer->startCountdown();

$x = preprocessValue($_POST["x"]);
$y = preprocessValue($_POST["y"]);
$r = preprocessValue($_POST["r"]);

$dataIsCorrect = isArgumentsAreNumbers($x, $y, $r) && checkX($x) && checkY($y) && checkR($r);

echo date(DATE_ATOM, time()) . " | " . changeCommaToPoint($x) . " | " . changeCommaToPoint($y) . " | " . changeCommaToPoint($r) . " | ";

if (!$dataIsCorrect) {
    echo "Ошибка ввода данных";
} else if (isHit($x, $y, $r)) {
    echo "Попал";
} else {
    echo "Не попал";
}

$interval = changePointToComma($timer->stopCountdown()) . " ms";

echo " | $interval";