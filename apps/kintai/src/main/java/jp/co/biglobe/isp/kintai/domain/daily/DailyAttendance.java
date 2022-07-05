package jp.co.biglobe.isp.kintai.domain.daily;

public record DailyAttendance(
        AttendanceDate attendanceDate,
        AttendanceDuration attendanceDuration,
        WorkTimeMinutes workTimeMinutes,
        OvertimeMinutes overtimeMinutes) {
}
