package com.naosim.dddwork.domain;

import java.time.LocalDate;

public class WorkTime {
    LocalDate workingDate;
    Hour workingStartHour;
    Minute workingStartMinute;
    Hour workingEndHour;
    Minute workingEndMinute;

    public WorkTime(
            LocalDate workingDate,
            Hour workingStartHour, Minute workingStartMinute,
            Hour workingEndHour, Minute workingEndMinute
    ) {
        this.workingDate = workingDate;
        this.workingStartHour = workingStartHour;
        this.workingStartMinute = workingStartMinute;
        this.workingEndHour = workingEndHour;
        this.workingEndMinute = workingEndMinute;
    }

    public LocalDate getWorkingDate() {
        return workingDate;
    }

    public Hour getWorkingStartHour() {
        return workingStartHour;
    }

    public Minute getWorkingStartMinute() {
        return workingStartMinute;
    }

    public Hour getWorkingEndHour() {
        return workingEndHour;
    }

    public Minute getWorkingEndMinute() {
        return workingEndMinute;
    }
}

//規定規則
//休憩時間１時間だけじゃない
