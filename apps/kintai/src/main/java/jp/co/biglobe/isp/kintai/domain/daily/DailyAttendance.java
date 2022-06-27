package jp.co.biglobe.isp.kintai.domain.daily;

public record DailyAttendance(
        AttendanceDate attendanceDate,
        AttendanceStartTime startTime,
        AttendanceEndTime endTime,
        WorkTimeMinutes workTimeMinutes,
        OvertimeMinutes overtimeMinutes) {
    public DailyAttendance create(
            AttendanceDate attendanceDate,
            AttendanceStartTime attendanceStartTime,
            AttendanceEndTime attendanceEndTime) {

        final WorkTimeMinutes workTimeMinutes = WorkTimeMinutes.calculateWorkTimeMinutes(
                attendanceStartTime,
                attendanceEndTime
        );
        final OvertimeMinutes overtimeMinutes = OvertimeMinutes.create(workTimeMinutes);

        return new DailyAttendance(
                attendanceDate,
                attendanceStartTime,
                attendanceEndTime,
                workTimeMinutes,
                overtimeMinutes
        );
    }
}
