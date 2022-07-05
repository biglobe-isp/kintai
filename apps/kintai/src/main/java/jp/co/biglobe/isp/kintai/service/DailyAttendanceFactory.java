package jp.co.biglobe.isp.kintai.service;

import jp.co.biglobe.isp.kintai.domain.daily.AttendanceDate;
import jp.co.biglobe.isp.kintai.domain.daily.AttendanceDuration;
import jp.co.biglobe.isp.kintai.domain.daily.AttendanceEndTime;
import jp.co.biglobe.isp.kintai.domain.daily.AttendanceStartTime;
import jp.co.biglobe.isp.kintai.domain.daily.DailyAttendance;
import jp.co.biglobe.isp.kintai.domain.daily.OvertimeMinutes;
import jp.co.biglobe.isp.kintai.domain.daily.WorkTimeMinutes;

public class DailyAttendanceFactory {
    public DailyAttendance create(
            AttendanceDate attendanceDate,
            AttendanceDuration attendanceDuration) {

        final WorkTimeMinutes workTimeMinutes = attendanceDuration.calculateWorkTimeMinutes();
        final OvertimeMinutes overtimeMinutes = OvertimeMinutes.calculate(workTimeMinutes);

        return new
                DailyAttendance(
                attendanceDate,
                attendanceDuration,
                attendanceDuration.calculateWorkTimeMinutes(),
                overtimeMinutes
        );
    }
}