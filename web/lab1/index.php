<html>
    <head>
        <title>Главная</title>
        <script src="js/coords-sender.js"></script>
        <script src="js/form-checker.js"></script>
        <script src="js/ajax-sender.js"></script>
        <?php
            require "php/generators.php";
        ?>
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
    </body>
</html>
