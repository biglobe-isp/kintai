package jp.co.biglobe.isp.kintai.datasource;

import jp.co.biglobe.isp.kintai.domain.daily.AttendanceDate;
import jp.co.biglobe.isp.kintai.domain.daily.AttendanceEndTime;
import jp.co.biglobe.isp.kintai.domain.daily.AttendanceStartTime;
import jp.co.biglobe.isp.kintai.domain.daily.DailyAttendance;
import jp.co.biglobe.isp.kintai.domain.daily.OvertimeMinutes;
import jp.co.biglobe.isp.kintai.domain.daily.WorkTimeMinutes;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

record DailyAttendanceCsv(
        String attendanceDate,
        String attendanceStartTime,
        String attendanceEndTime,
        String workTimeMinutes,
        String overTimeMinutes,
        String updatedAt) {
    public DailyAttendance toDomain(
            DateTimeFormatter attendanceDateFormatter,
            DateTimeFormatter attendanceTimeFormatter) {
        return new DailyAttendance(
                new AttendanceDate(LocalDate.parse(attendanceDate, attendanceDateFormatter)),
                new AttendanceStartTime(LocalTime.parse(attendanceStartTime, attendanceTimeFormatter)),
                new AttendanceEndTime(LocalTime.parse(attendanceEndTime, attendanceTimeFormatter)),
                new WorkTimeMinutes(Integer.valueOf(workTimeMinutes)),
                new OvertimeMinutes(Integer.valueOf(overTimeMinutes))
        );
    }
}
