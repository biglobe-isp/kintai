package com.sample.kintai.domain;

public class WorkMinutes {
    private final StartTime startTime;
    private final EndTime endTime;

    public WorkMinutes(StartTime startTime, EndTime endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }

    private int calcWorkMinutes() {
        return endTime.calcMinites() - startTime.calcMinutes();
    }

    public int calcWorkMinutesIncludingBreakTime() {
        int workMinutes = calcWorkMinutes();
        int endH = endTime.getHour();
        int endM = endTime.getMinute();
        if (endH == 12) {
            workMinutes -= endM;
        } else if (endH >= 13) {
            workMinutes -= 60;
        }

        if (endH == 18) {
            workMinutes -= endM;
        } else if (endH >= 19) {
            workMinutes -= 60;
        }

        if (endH == 21) {
            workMinutes -= endM;
        } else if (endH >= 22) {
            workMinutes -= 60;
        }
        return workMinutes;
    }

    public int calcOverWorkMinutes() {
        return Math.max(calcWorkMinutes() - 8 * 60, 0);
    }
}