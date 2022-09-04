<?php
session_start();

require_once "Timer.php";
require "Row.php";
require "utils.php";
require "hit-checker.php";
require "data-validator.php";

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
$_SESSION["rows"] = $_SESSION["rows"] . $row->getData();
?>
<table>
    <tr>
        <td>Время</td>
        <td>Координаты</td>
        <td>Попадание</td>
        <td>Время выполнения скрипта</td>
    </tr>
    <?=$_SESSION["rows"]?>
    <!--<?php //echo $row->getData(); ?>-->
</table>