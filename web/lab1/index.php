<?php
    require "php/generators.php";
    require "php/rows-printer.php";
    session_start();
?>
<html>
    <head>
        <title>Лабораторная работа №1</title>
        <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
        <script src="js/coords-sender.js"></script>
        <script src="js/form-checker.js"></script>
        <script src="js/ajax-sender.js"></script>
    </head>
    <body>
        <div id="preview">
            <p>Шульга Артём Игоревич P32XX1
            <p>Вариант - <b>1819</b>
        </div>
        <div id="form">
            <p>*Картинка*
            <form name="coords">
                <p>X:
                <?php generate_radio_buttons(-5, 3); ?>
                <p>Y:
                <input type="text" name="y" require placeholder="-5..5">
                <p>R:
                <select name = "r" require>
                    <?php generate_options(1, 5); ?>
                </select>
                <input type="button" value="Отправить" onclick="sendCoordinates();">
            </form>
        </div>
        <div id="table">
            <table>
                <tr>
                    <td>Время</td>
                    <td>Координаты</td>
                    <td>Попадание</td>
                    <td>Время выполнения скрипта</td>
                </tr>
                <tfoot id="receivingData">
                <?php
                if(isset($_SESSION["rows"])) {
                    printRows($_SESSION["rows"]);
                }
                ?>
                </tfoot>
            </table>
        </div>
    </body>
</html>
