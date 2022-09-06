<?php
function generate_radio_buttons($min_value, $max_value, $step)
{
    $template = "%d:<input class =\"xCoordinate\" type=\"radio\" name=\"x\" value=\"%d\">";

    for ($i = $min_value; $i < $max_value + 1; $i+=$step) {
        printf($template, $i, $i);
    }
}

function generate_options($min_value, $max_value, $step)
{
    $template = "<option>%d</option>";
    
    for ($i = $min_value; $i < $max_value + 1; $i+=$step) {
        printf($template, $i);
    }

}
