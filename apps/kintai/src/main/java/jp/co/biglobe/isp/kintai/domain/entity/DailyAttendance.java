package jp.co.biglobe.isp.kintai.domain.entity;

import jp.co.biglobe.isp.kintai.domain.AttendanceDate;
import jp.co.biglobe.isp.kintai.domain.AttendanceEndTime;
import jp.co.biglobe.isp.kintai.domain.AttendanceStartTime;
import jp.co.biglobe.isp.kintai.domain.OvertimeMinutes;
import jp.co.biglobe.isp.kintai.domain.WorkTimeMinutes;

public record DailyAttendance(
        AttendanceDate attendanceDate,
        AttendanceStartTime startTime,
        AttendanceEndTime endTime,
        WorkTimeMinutes workTimeMinites,
        OvertimeMinutes overtimeMinites) {
}
