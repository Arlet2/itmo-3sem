<?php

class Timer
{
    private $startTime;

    public function startCountdown()
    {
        $this->startTime = hrtime(true);
    }

    public function stopCountdown(): float
    {
        return round((hrtime(true) - $this->startTime) / (10 ** 6), 3);
    }
}
