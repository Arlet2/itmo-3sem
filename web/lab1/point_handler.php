<?php

$startTime = hrtime(true);

require "hit_checker.php";

$x = $_POST["x"];
$y = $_POST["y"];
$r = $_POST["r"];

echo $startTime . " | " . $x . " | " . $y . " | " . $r . " | ";

if (isHit($x, $y, $r)) {
    echo "Попал";
} else {
    echo "Не попал";
}

$interval = round((hrtime(true) - $startTime)/(10**6), 3) . " ms";

echo " | " . $interval;