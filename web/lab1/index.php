<html>
    <head>
        <title>Главная</title>
    </head>
    <body>
        <div id="preview">
            <p>Шульга Артём Игоревич P32XX1
            <p>Вариант - <b>1819</b>
        </div>
        <div id="form">
            <p>*Картинка*
            <form name="coords" method="POST">
                <p>X:
                <?php
                    $template = "<input type=\"radio\" name=\"x\" value=\"%d\">";

                    $min_value = -5;
                    $max_value = 3;

                    for($i=$min_value;$i<$max_value+1;$i++) {
                        echo "$i:";
                        printf($template, $i);
                    }
                ?>
                <p>Y:
                <input type="text" name="y">
                <p>R:
                <select>
                    <?php
                        $template = "<option>%d</option>";

                        $min_value = 1;
                        $max_value = 5;

                        for($i = $min_value; $i<$max_value+1;$i++)
                            printf($template, $i);
                    ?>
                </select>
                <input type="submit">
            </form>
        </div>
    </body>
</html>
