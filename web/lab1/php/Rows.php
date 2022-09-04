<?php
class Rows
{
    private $MAX_SIZE_OF_ROWS = 7;
    private $rows = array();

    public function __construct($first_row)
    {
        array_push($this->rows, $first_row);
    }

    public function pushBack($row)
    {
        if (count($this->rows) > $this->MAX_SIZE_OF_ROWS) {
            array_shift($this->rows);
        }
        array_push($this->rows, $row);
    }

    public function getValues() : array
    {
        return $this->rows;
    }
}