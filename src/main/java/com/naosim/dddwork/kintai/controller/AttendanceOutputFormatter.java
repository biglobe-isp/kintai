package com.naosim.dddwork.kintai.controller;

import com.naosim.dddwork.kintai.domain.timerecord.attendance.AttendanceRecord;
import org.springframework.stereotype.Component;

@Component
class AttendanceOutputFormatter {
    String toAllOutput(AttendanceRecord attendanceRecord) {
        StringBuilder builder = new StringBuilder();
        String separator = System.getProperty("line.separator");
        builder
                .append(getSuccessfulNotification())
                .append(separator)
                .append(getAttendanceDate(attendanceRecord))
                .append(separator)
                .append(getAttendanceStartEnd(attendanceRecord))
                .append(separator)
                .append(getWorkingTime(attendanceRecord))
                .append(separator)
                .append(getOvertime(attendanceRecord));
        return builder.toString();
    }

    private String getSuccessfulNotification() {
        return "打刻が完了しました。";
    }

    private String getAttendanceDate(AttendanceRecord attendanceRecord) {
        return "出勤日：" + attendanceRecord.getAttendanceLocalDate();
    }

    private String getAttendanceStartEnd(AttendanceRecord attendanceRecord) {
        return String.format(
                "%s - %s",
                attendanceRecord.getAttendanceStartLocalTime(),
                attendanceRecord.getAttendanceEndLocalTime()
        );
    }

    private String getWorkingTime(AttendanceRecord attendanceRecord) {
        return "労働時間：" +
                String.format(
                        "%s時間%s分",
                        attendanceRecord.getActualMinutes().convertActualWorkingTimeMinutesToHour(),
                        attendanceRecord.getActualMinutes().extractRemainderActualWorkingTimeMinutes()
                );
    }

    private String getOvertime(AttendanceRecord attendanceRecord) {
        return "残業時間：" +
                String.format(
                        "%s時間%s分",
                        attendanceRecord.getActualMinutes().convertActualOvertimeMinutesToHour(),
                        attendanceRecord.getActualMinutes().extractRemainderActualOvertimeMinutes()
                );
    }
}
