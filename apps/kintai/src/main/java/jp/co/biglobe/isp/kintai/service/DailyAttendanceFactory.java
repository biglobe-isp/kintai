package jp.co.biglobe.isp.kintai.service;

import jp.co.biglobe.isp.kintai.domain.daily.AttendanceDate;
import jp.co.biglobe.isp.kintai.domain.daily.AttendanceEndTime;
import jp.co.biglobe.isp.kintai.domain.daily.AttendanceStartTime;
import jp.co.biglobe.isp.kintai.domain.daily.DailyAttendance;
import jp.co.biglobe.isp.kintai.domain.daily.OvertimeMinutes;
import jp.co.biglobe.isp.kintai.domain.daily.WorkTimeMinutes;

public class DailyAttendanceFactory {
    public DailyAttendance create(
            AttendanceDate attendanceDate,
            AttendanceStartTime attendanceStartTime,
            AttendanceEndTime attendanceEndTime) {

        final WorkTimeMinutes workTimeMinutes = WorkTimeMinutes.calculateWorkTimeMinutes(
                attendanceStartTime,
                attendanceEndTime
        );
        final OvertimeMinutes overtimeMinutes = OvertimeMinutes.create(workTimeMinutes);

        return new
                DailyAttendance(
                attendanceDate,
                attendanceStartTime,
                attendanceEndTime,
                workTimeMinutes,
                overtimeMinutes
        );
    }
}