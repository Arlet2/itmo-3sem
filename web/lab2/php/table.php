<?php

require "Row.php";
require "Rows.php";
require_once "Timer.php";
require "utils.php";
require "hit-checker.php";
require "data-validator.php";
require "rows-printer.php";

session_start();

$timer = new Timer();

$timer->startCountdown();

$row;
if ($_SERVER['REQUEST_METHOD'] == 'POST') {
    $x = preprocessValue($_POST["x"]);
    $y = preprocessValue($_POST["y"]);
    $r = preprocessValue($_POST["r"]);

    $dataIsCorrect = isArgumentsAreNumbers($x, $y, $r) && checkX($x) && checkY($y) && checkR($r);

    $dataInfo;

    if (!$dataIsCorrect) {
        $dataInfo = "Ошибка ввода данных";
    } else if (isHit($x, $y, $r)) {
        $dataInfo = "Попал";
    } else {
        $dataInfo = "Не попал";
    }

    $row = new Row(date(DATE_ATOM, time()), $_POST["x"], $_POST["y"], $_POST["r"], $dataInfo, changePointToComma($timer->stopCountdown()) . " ms");
} else {
    $row = new Row("PLEASE", "USE", "ANOTHER", "METHOD", "FOR", "SENDING!");
}

$rows;

if (!isset($_SESSION["rows"])) {
    $rows = new Rows($row);
} else {
    $rows = $_SESSION["rows"];
    $rows->pushFront($row);
}

$_SESSION["rows"] = $rows;
?>

<?php
printRows($rows);
?>