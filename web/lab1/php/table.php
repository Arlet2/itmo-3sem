<?php
session_start();

require_once "Timer.php";
require "utils.php";
require "hit-checker.php";
require "data-validator.php";

$timer = new Timer();

$timer->startCountdown();

if ($_SERVER['REQUEST_METHOD'] == 'POST') {
    $x = preprocessValue($_POST["x"]);
    $y = preprocessValue($_POST["y"]);
    $r = preprocessValue($_POST["r"]);

    $dataIsCorrect = isArgumentsAreNumbers($x, $y, $r) && checkX($x) && checkY($y) && checkR($r);
} else {
    $x = "UNKNOWN";
    $y = "UNKNOWN";
    $r = "UNKNOWN";

    $dataIsCorrect = false;
}
?>
<table>
    <tr>
        <td>Время</td>
        <td>Координаты</td>
        <td>Попадание</td>
        <td>Время выполнения скрипта</td>
    </tr>
    <tr>
        <td><?=date(DATE_ATOM, time());?></td>
        <td>X: <?=changePointToComma($x)?>
            <p>Y: <?=changePointToComma($y);?>
            <p>Z: <?=changePointToComma($r);?>
        </td>
        <td>
            <?php
            if (!$dataIsCorrect) {
                echo "Ошибка ввода данных";
            } else if (isHit($x, $y, $r)) {
                echo "Попал";
            } else {
                echo "Не попал";
            }
            ?>
        </td>
        <td><?=changePointToComma($timer->stopCountdown()) . " ms"?></td>
    </tr>
</table>