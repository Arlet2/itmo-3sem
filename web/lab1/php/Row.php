<?php
class Row
{
    private $time;
    private $x;
    private $y;
    private $r;
    private $dataInfo;
    private $interval;

    public function __construct($time, $x, $y, $r, $dataInfo, $interval)
    {
        $this->time = $time;

        $this->x = $x;
        $this->y = $y;
        $this->r = $r;

        $this->dataInfo = $dataInfo;
        $this->interval = $interval;
    }

    public function getData(): string
    {
        return
            "<tr>
        <td> $this->time </td>
        <td>X: $this->x
            <p>Y: $this->y
            <p>Z: $this->r
        </td>
        <td>$this->dataInfo</td>
        <td>$this->interval</td>
        </tr>";
    }

}
