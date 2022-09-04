<?php
class Rows
{
    private $MAX_SIZE_OF_ROWS = 7;
    private $rows = array();

    public function __construct($first_row)
    {
        array_push($this->rows, $first_row);
    }

    public function pushFront($row)
    {
        if (count($this->rows) > $this->MAX_SIZE_OF_ROWS) {
            array_pop($this->rows);
        }
        array_unshift($this->rows, $row);
    }

    public function getValues() : array
    {
        return $this->rows;
    }
}