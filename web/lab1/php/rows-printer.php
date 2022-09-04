<?php
require_once "Rows.php";
require_once "Row.php";
function printRows($rows)
{
    foreach ($rows->getValues() as &$printingRow) {
        echo $printingRow->getData() . "\n";
    }    
}