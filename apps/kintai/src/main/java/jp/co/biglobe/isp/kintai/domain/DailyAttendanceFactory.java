package jp.co.biglobe.isp.kintai.domain;

import apps.kintai.src.main.java.jp.co.biglobe.isp.kintai.domain.rule.AttendanceRule;
import jp.co.biglobe.isp.kintai.api.InputArgs;
import jp.co.biglobe.isp.kintai.domain.entity.DailyAttendance;

import java.time.temporal.ChronoUnit;

public class DailyAttendanceFactory {
    private final AttendanceRule attendanceRule;

    public DailyAttendanceFactory(AttendanceRule attendanceRule) {
        this.attendanceRule = attendanceRule;
    }

    public DailyAttendance create(InputArgs inputArgs) {
        final AttendanceDate attendanceDate = AttendanceDate.of(inputArgs.attendanceDate());
        final AttendanceStartTime attendanceStartTime = AttendanceStartTime.of(inputArgs.attendanceStartTime());
        final AttendanceEndTime attendanceEndTime = AttendanceEndTime.of(inputArgs.attendanceEndTime());
        final WorkTimeMinutes workTimeMinutes = calculateWorkTimeMinutes(attendanceStartTime, attendanceEndTime);
        final OvertimeMinutes overtimeMinutes = calculateOverTimeMinutes(workTimeMinutes);

        final DailyAttendance dailyAttendance = new DailyAttendance(
                attendanceDate,
                attendanceStartTime,
                attendanceEndTime,
                workTimeMinutes,
                overtimeMinutes
        );
        return dailyAttendance;
    }

    private WorkTimeMinutes calculateWorkTimeMinutes(
            AttendanceStartTime attendanceStartTime,
            AttendanceEndTime attendanceEndTime) {
        long startToEndMinutes = ChronoUnit.MINUTES.between(attendanceStartTime.value(), attendanceEndTime.value());

        return new WorkTimeMinutes(0);
    }

    private OvertimeMinutes calculateOverTimeMinutes(WorkTimeMinutes workTimeMinutes) {
        return new OvertimeMinutes(0);
    }
}
