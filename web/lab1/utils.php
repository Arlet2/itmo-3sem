<?php
function changeCommaToPoint($value): string
{
    return str_replace(",", ".", $value);
}

function clearSpaces($value): string
{
    return str_replace(" ", "", $value);
}

function changePointToComma($value): string
{
    return str_replace(".", ",", $value);
}
