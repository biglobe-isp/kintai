package com.naosim.dddwork.domain.attendance.total;

public class AttendanceTotalData {
    private final Integer workTimeMinutesSum;
    private final Integer overWorkTimeMinutesSum;

    public AttendanceTotalData(Integer workTimeMinutesSum, Integer overWorkTimeMinutesSum) {
        if (workTimeMinutesSum < 0) {
            throw new IllegalArgumentException("workTimeMinutesSum must be greater than 0");
        }
        if (overWorkTimeMinutesSum < 0) {
            throw new IllegalArgumentException("overWorkTimeMinutesSum must be greater than 0");
        }
        this.workTimeMinutesSum = workTimeMinutesSum;
        this.overWorkTimeMinutesSum = overWorkTimeMinutesSum;
    }

    public Integer getWorkTimeMinutesSum() {
        return workTimeMinutesSum;
    }

    public Integer getOverWorkTimeMinutesSum() {
        return overWorkTimeMinutesSum;
    }
}
