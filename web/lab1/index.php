<html>
    <head>
        <title>Главная</title>
        <script src="form_checker.js"></script>
        <script src="ajax_sender.js"></script>
        <?php
            require "generators.php";
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
                <input type="text" name="y" require>
                <p>R:
                <select name = "r" require>
                    <?php generate_options(1, 5); ?>
                </select>
                <input type="button" value="Отправить" onclick="sendRequest('POST', 'point_handler.php');">
            </form>
        </div>
    </body>
    <?php
    echo "<p>DATA:";
    if (isset($_POST["x"]))
        echo "<p>x: " . $_POST["x"];
    if (isset($_POST["y"]))
        echo "<p>y: " . $_POST["y"];
    if (isset($_POST["r"]))
        echo "<p>r: " . $_POST["r"];
    ?>
</html>
