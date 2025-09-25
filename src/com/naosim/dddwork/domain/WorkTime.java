package com.naosim.dddwork.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class WorkTime {
    LocalDate workingDate;
    Hour workingStartHour;
    Minute workingStartMinute;
    Hour workingEndHour;
    Minute workingEndMinute;
    LocalDateTime inputDate;

    public WorkTime(
            LocalDate workingDate,
            Hour workingStartHour, Minute workingStartMinute,
            Hour workingEndHour, Minute workingEndMinute,
            LocalDateTime inputDate) {
        this.workingDate = workingDate;
        this.workingStartHour = workingStartHour;
        this.workingStartMinute = workingStartMinute;
        this.workingEndHour = workingEndHour;
        this.workingEndMinute = workingEndMinute;
        this.inputDate = inputDate;
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

    public LocalDateTime getInputDate() {
        return inputDate;
    }

    public WorkMinutes calculateWorkTimeMinutes(Hour lunchBreak, Hour eveningBreak, Hour nightBreak) {
        int endWorkTimeMinutes = workingEndHour.getHour() * 60 + workingEndMinute.getMinute();
        int startWorkTimeMinutes = workingStartHour.getHour() * 60 + workingStartMinute.getMinute();
        int workMinutes = endWorkTimeMinutes - startWorkTimeMinutes;
        if (workingEndHour.getHour() == lunchBreak.getHour()) {
            workMinutes -= workingEndMinute.getMinute();
        } else if (workingEndHour.getHour() >= lunchBreak.getHour() + 1) {
            workMinutes -= 60;
        }

        if (workingEndHour.getHour() == eveningBreak.getHour()) {
            workMinutes -= workingEndMinute.getMinute();
        } else if (workingEndHour.getHour() >= eveningBreak.getHour() + 1) {
            workMinutes -= 60;
        }

        if (workingEndHour.getHour() == nightBreak.getHour()) {
            workMinutes -= workingEndMinute.getMinute();
        } else if (workingEndHour.getHour() >= nightBreak.getHour() + 1) {
            workMinutes -= 60;
        }
        return new WorkMinutes(workMinutes);
    }

    public WorkMinutes calculateOverWorkTimeMinutes(Hour lunchBreak, Hour eveningBreak, Hour nightBreak) {
        int workMinutes = this.calculateWorkTimeMinutes(lunchBreak, eveningBreak, nightBreak).getWorkMinutes();
        int overMinutes = Math.max(workMinutes - 8 * 60, 0);
        return new WorkMinutes(overMinutes);
    }
}
