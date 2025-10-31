package com.naosim.dddwork.domain;

public class WorkTimeMinutesCalculator {
    public int calculateWorkTimeMinutes(WorkTime workTime) {

        int endWorkTimeMinutes = workTime.getWorkingEndHour().getHour() * 60 + workTime.getWorkingEndMinute()
                .getMinute();
        int startWorkTimeMinutes = workTime.getWorkingStartHour().getHour() * 60 + workTime.getWorkingStartMinute()
                .getMinute();
        int workMinutes = endWorkTimeMinutes - startWorkTimeMinutes;
        for (int restHour : WorkRule.getRestHourList()) {
            if (workTime.getWorkingEndHour().getHour() == restHour) {
                workMinutes -= workTime.getWorkingEndMinute().getMinute();
            } else if (workTime.getWorkingEndHour().getHour() >= restHour + 1) {
                workMinutes -= 60;
            }
        }
        if (workMinutes < 0)
            throw new IllegalArgumentException("時間が不正です。");

        return workMinutes;
    }
}
