package jp.co.biglobe.isp.kintai.domain.daily;

import jp.co.biglobe.isp.kintai.domain.rule.OffHours;

import java.time.temporal.ChronoUnit;

public record AttendanceDuration(AttendanceStartTime attendanceStartTime, AttendanceEndTime attendanceEndTime) {
    public WorkTimeMinutes calculateWorkTimeMinutes() {
        final int attendanceTimeMinutes = (int) ChronoUnit.MINUTES.between(
                attendanceStartTime.value(),
                attendanceEndTime.value()
        );
        final int actualTotalBreakMinutes = OffHours.calcActualTotalBreakTime(
                attendanceStartTime,
                attendanceEndTime,
                ChronoUnit.MINUTES
        );
        return new WorkTimeMinutes(attendanceTimeMinutes - actualTotalBreakMinutes);
    }
}
