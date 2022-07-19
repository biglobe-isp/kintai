package jp.co.biglobe.isp.kintai.domain.daily;

import jp.co.biglobe.isp.kintai.domain.rule.OffHours;

public class DailyAttendanceFactory {
    public DailyAttendance create(
            AttendanceDate attendanceDate,
            AttendanceDuration attendanceDuration) {

        final WorkTimeMinutes workTimeMinutes = attendanceDuration.calculateWorkTimeMinutes(OffHours::calculateActualTotalBreakTime);
        final OvertimeMinutes overtimeMinutes = OvertimeMinutes.calculate(workTimeMinutes);

        return new
                DailyAttendance(
                attendanceDate,
                attendanceDuration,
                workTimeMinutes,
                overtimeMinutes
        );
    }
}