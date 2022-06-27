package jp.co.biglobe.isp.kintai.domain.daily;

import jp.co.biglobe.isp.kintai.domain.rule.OffHours;

import java.time.temporal.ChronoUnit;

public record WorkTimeMinutes(int value) {
    public static WorkTimeMinutes calculateWorkTimeMinutes(
            AttendanceStartTime attendanceStartTime, AttendanceEndTime attendanceEndTime) {
        final int attendanceTimeMinutes = (int) ChronoUnit.MINUTES.between(
                attendanceStartTime.value(),
                attendanceEndTime.value()
        );
        final int actualTotalBreakMinutes = OffHours.calcActualTotalBreak(
                attendanceStartTime,
                attendanceEndTime,
                ChronoUnit.MINUTES
        );
        return new WorkTimeMinutes(attendanceTimeMinutes - actualTotalBreakMinutes);
    }
}

