<?php

$startTime = hrtime(true);

require "utils.php";
require "hit_checker.php";
require "data_validator.php";

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

$interval = changePointToComma(round((hrtime(true) - $startTime) / (10 ** 6), 3)) . " ms";

echo " | " . $interval;