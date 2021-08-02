package com.naosim.dddwork.domain;

public class TimeSpan {
    private final int startTimeHour;
    private final int startTimeMinute;
    private final int endTimeHour;
    private final int endTimeMinute;

    public TimeSpan(String start, String end) {
        if (!start.matches("[0-9]{4}")) {
            throw new RuntimeException("Invalid Parameter: start");
        }
        if (!end.matches("[0-9]{4}")) {
            throw new RuntimeException("Invalid Parameter: end");
        }
        this.startTimeHour = Integer.valueOf(start.substring(0, 2));
        this.startTimeMinute = Integer.valueOf(start.substring(2, 4));
        this.endTimeHour = Integer.valueOf(end.substring(0, 2));
        this.endTimeMinute = Integer.valueOf(end.substring(2, 4));
    }

    public int getStartTimeHour() {
        return this.startTimeHour;
    }
    public int getStartTimeMinute() {
        return this.startTimeMinute;
    }
    public int getEndTimeHour() {
        return this.endTimeHour;
    }
    public int getEndTimeMinute() {
        return this.endTimeMinute;
    }
    public int getMinutes() {
        int minutes = (this.endTimeHour - this.startTimeHour) * 60;
        if (this.endTimeMinute - this.startTimeMinute < 0) {
            minutes += ((this.endTimeMinute + 60) - this.startTimeMinute) - 60;
        } else {
            minutes += this.endTimeMinute - this.startTimeMinute;
        }
        return minutes;
    }
}