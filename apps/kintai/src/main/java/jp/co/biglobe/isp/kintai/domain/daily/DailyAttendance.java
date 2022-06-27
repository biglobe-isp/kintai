package jp.co.biglobe.isp.kintai.domain.daily;

public record DailyAttendance(
        AttendanceDate attendanceDate,
        AttendanceStartTime startTime,
        AttendanceEndTime endTime,
        WorkTimeMinutes workTimeMinutes,
        OvertimeMinutes overtimeMinutes) {
}
