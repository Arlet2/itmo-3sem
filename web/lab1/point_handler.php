<?php

$startTime = microtime(true);

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

$interval = round((microtime(true) - $startTime)*1000, 3) . " ms";

echo " | " . $interval;