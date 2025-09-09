package com.naosim.dddwork.domain;

public class calculateWorkingTime {
    private workTimeHours workTimeStartHour;
    private workTimeHours workTimeEndHour;
    private workTimeMinutes workTimeStartMinute;
    private workTimeMinutes workTimeEndMinute;

    public int calculateWorkTimeMinutes() {
        workTimeMinutes workMinutes = workTimeEndHour * 60 + workTimeEndMinute - (workTimeStartHour * 60 + workTimeStartMinute);
        return workMinutes;
    }
}
