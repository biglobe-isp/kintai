package com.naosim.dddwork.domain;

import java.time.Duration;

public class  AttendanceSummary
{
    public Duration regularTime = null;
    public Duration overTime = null;
    public boolean fired;

    public AttendanceSummary()
    {
        regularTime =  Duration.ofMinutes(0);
        overTime =  Duration.ofMinutes(0);
        fired = false;
    }

    public String toString()
    {
        return regularTime.toHours() + ":" + regularTime.toMinutes() % 60 + "/" +  overTime.toHours() + ":" + overTime.toMinutes() % 60;
    }
}