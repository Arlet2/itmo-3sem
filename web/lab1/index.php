<?php
require "php/generators.php";
require "php/rows-printer.php";
session_start();
?>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Лабораторная работа №1</title>
        <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
        <script src="js/coords-sender.js"></script>
        <script src="js/form-checker.js"></script>
        <script src="js/ajax-sender.js"></script>

        <style>
            /* наследование */
            body {
                text-align: center;
                margin-left: 100px;
                margin-right: 100px;
                margin-top: 0px;
                margin-bottom: 20px;
            }
            table {
                align-content: center;
                width: 100%;
                border-collapse: collapse;
                background: white;
            }

            tr {
                text-align: center;
            }

            td {
                vertical-align: top;
            }

            /* каскадирование */
            table.head {
                padding: 5px;
                width: 100%;
            }

            .head p {
                font-family: serif, 'Times New Roman', Times;
                font-size: 14pt;
                color: black;
            }
            
            th {
                border: 2px solid;
            }

            p.task {
                text-align: left;
            }

            .dataRow {
                border: 1px solid;
            }

            input[type="radio"] {
                padding-left: 15px;
                padding-right: 15px;
            }
            
            li.task {
                text-align: left;
            }

            table.radioButtons {
                height: 100%;
            }

            td.head {
                width: 50%;
            }

            td.head:first-child {
                text-align: right;
            }
            
            td.head:last-child {
                text-align: left;
            }
            
            /* дочерние элементы */
            form[name = "coords"] > p {
                font-weight: bold;
            }
        </style>
    </head>
    <body>
        <table class = "mainTable">
            <tr class="head">
                <table class="head">
                    <tr>
                        <td class="head"><p>Шульга Артём Игоревич P32111</td>
                        <td class="head"><p>Вариант - <b>1819</b></td>
                    </tr>
                </table>
            </tr>
            <tr>
                <table class = "mainBody">
                    <tr class = "task">
                    <p class = "task">Разработать PHP-скрипт, определяющий попадание точки на координатной плоскости 
                        в заданную область, и создать HTML-страницу, которая формирует данные 
                        для отправки их на обработку этому скрипту.

                    <p class = "task">Параметр R и координаты точки должны передаваться скрипту посредством HTTP-запроса. 
                        Скрипт должен выполнять валидацию данных и возвращать HTML-страницу с таблицей, 
                        содержащей полученные параметры и результат вычислений - факт попадания 
                        или непопадания точки в область. 
                        Предыдущие результаты должны сохраняться между запросами и отображаться в таблице.
                    <p class = "task">Кроме того, ответ должен содержать данные о текущем времени и времени работы скрипта.
                    <p class = "task"><b>Разработанная HTML-страница должна удовлетворять следующим требованиям:</b>
                    <ul>
                        <li class = "task">Для расположения текстовых и графических элементов необходимо использовать табличную верстку.</li>
                        <li class = "task">Данные формы должны передаваться на обработку посредством POST-запроса.</li>
                        <li class = "task">Таблицы стилей должны располагаться в самом веб-документе.</li>
                        <li class = "task">При работе с CSS должно быть продемонстрировано использование селекторов псевдоклассов, 
                            селекторов дочерних элементов, селекторов атрибутов, 
                            селекторов потомств а также такие свойства стилей CSS, 
                            как наследование и каскадирование.</li>
                        <li class = "task">HTML-страница должна иметь "шапку", содержащую ФИО студента, 
                            номер группы и новер варианта. 
                            При оформлении шапки необходимо явным образом задать шрифт (serif), 
                            его цвет и размер в каскадной таблице стилей.</li>
                        <li class = "task">Отступы элементов ввода должны задаваться в пикселях.</li>
                        <li class = "task">Страница должна содержать сценарий на языке JavaScript, 
                            осуществляющий валидацию значений, вводимых пользователем в поля формы. 
                            Любые некорректные значения (например, 
                            буквы в координатах точки или отрицательный радиус) должны блокироваться.</li>
                    </ul>
                    </tr>
                    <tr><h2><b>Ввод данных</b></h2></tr>
                    <tr>
                        <td>
                            <img src="images/info.png">
                        </td>
                        <td>
                            <form name="coords">
                                <table class ="form">
                                <tr>
                                    <p>X:
                                    <?php generate_radio_buttons(-5, 3, 1);?>
                                </tr>
                                <tr>
                                    <p>Y:
                                    <input type="text" name="y" require placeholder="-5..5">
                                </tr>
                                <tr>
                                    <p>R:
                                    <select name = "r" require>
                                        <?php generate_options(1, 5, 1);?>
                                    </select>
                                </tr>
                                <tr>
                                    <input type="button" value="Отправить" onclick="sendCoordinates();">
                                </tr>
                                </table>
                            </form>
                        </td>
                    </tr>
                </table>
            </tr>
            <tr>
                <h2><b>Таблица данных (последние 10 записей)</b></h2>
            </tr>
            <tr>
                <table class = "results">
                    <th>Время</td>
                    <th>Координаты</td>
                    <th>Попадание</td>
                    <th>Время выполнения скрипта</td>
                    <tfoot id="receivingData">
                    <?php
                    if (isset($_SESSION["rows"])) {
                        printRows($_SESSION["rows"]);
                    }
                    ?>
                    </tfoot>
                </table>
            </tr>
        </table>
    </body>
</html>
