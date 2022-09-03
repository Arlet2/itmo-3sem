<?php
function generate_radio_buttons($min_value, $max_value)
{
    $template = "<input type=\"radio\" name=\"x\" value=\"%d\">";

    for ($i = $min_value; $i < $max_value + 1; $i++) {
        echo "$i:";
        printf($template, $i);
    }
}

function generate_options($min_value, $max_value)
{
    $template = "<option>%d</option>";
    
    for ($i = $min_value; $i < $max_value + 1; $i++) {
        printf($template, $i);
    }

}
