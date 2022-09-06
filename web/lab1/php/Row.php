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
            "<tr class=\"dataRow\">
        <td class=\"dataRow\"> $this->time </td>
        <td class=\"dataRow\">X: $this->x
            <p>Y: $this->y
            <p>R: $this->r
        </td>
        <td class=\"dataRow\">$this->dataInfo</td>
        <td class=\"dataRow\">$this->interval</td>
        </tr>";
    }

}
