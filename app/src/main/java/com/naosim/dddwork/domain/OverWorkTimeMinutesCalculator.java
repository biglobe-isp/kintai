package com.naosim.dddwork.domain;

public class OverWorkTimeMinutesCalculator {
    public int calculateOverWorkTimeMinutes(WorkTime workTime) {
        WorkTimeMinutesCalculator calculator = new WorkTimeMinutesCalculator();

        int workMinutes = calculator.calculateWorkTimeMinutes(workTime);
        int overMinutes = Math.max(workMinutes - WorkRule.getRegularWorkingTime() * 60, 0);
        return overMinutes;
    }
}
