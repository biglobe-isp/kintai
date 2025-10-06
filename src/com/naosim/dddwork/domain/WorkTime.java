package com.naosim.dddwork.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WorkTime {
    private static final int regularWorkingTime = 8;
    LocalDate workingDate;
    Hour workingStartHour;
    Minute workingStartMinute;
    Hour workingEndHour;
    Minute workingEndMinute;
    List<Integer> restHourList = new ArrayList<Integer>(Arrays.asList(12, 18, 21));

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

    public int calculateWorkTimeMinutes() {
        int endWorkTimeMinutes = workingEndHour.getHour() * 60 + workingEndMinute.getMinute();
        int startWorkTimeMinutes = workingStartHour.getHour() * 60 + workingStartMinute.getMinute();
        int workMinutes = endWorkTimeMinutes - startWorkTimeMinutes;
        for (int restHour : restHourList) {
            if (workingEndHour.getHour() == restHour) {
                workMinutes -= workingEndMinute.getMinute();
            } else if (workingEndHour.getHour() >= restHour + 1) {
                workMinutes -= 60;
            }
        }
        if (workMinutes < 0)
            throw new IllegalArgumentException("時間が不正です。");

        return workMinutes;
    }

    public int calculateOverWorkTimeMinutes() {
        int workMinutes = this.calculateWorkTimeMinutes();
        int overMinutes = Math.max(workMinutes - regularWorkingTime * 60, 0);
        return overMinutes;
    }
}
