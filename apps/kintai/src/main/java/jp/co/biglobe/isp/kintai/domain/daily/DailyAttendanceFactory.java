package jp.co.biglobe.isp.kintai.domain.daily;

import jp.co.biglobe.isp.kintai.api.InputArgs;

public class DailyAttendanceFactory {
    public DailyAttendance create(InputArgs inputArgs) {
        final AttendanceDate attendanceDate = AttendanceDate.of(inputArgs.attendanceDate());
        final AttendanceStartTime attendanceStartTime = AttendanceStartTime.of(inputArgs.attendanceStartTime());
        final AttendanceEndTime attendanceEndTime = AttendanceEndTime.of(inputArgs.attendanceEndTime());
        final WorkTimeMinutes workTimeMinutes = WorkTimeMinutes.calculateWorkTimeMinutes(attendanceStartTime, attendanceEndTime);
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
