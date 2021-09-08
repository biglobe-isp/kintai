package com.naosim.dddwork.kintai.controller;

import com.naosim.dddwork.kintai.domain.timerecord.attendance.AttendanceRecord;
import lombok.NonNull;
import lombok.Value;

@Value
class AttendanceOutputItem {
    @NonNull
    AttendanceRecord attendanceRecord;

    String toAllOutput() {
        StringBuilder builder = new StringBuilder();
        String separator = System.getProperty("line.separator");
        builder
                .append(getSuccessfulNotification())
                .append(separator)
                .append(getAttendanceDate())
                .append(separator)
                .append(getAttendanceStartEnd())
                .append(separator)
                .append(getWorkingTime())
                .append(separator)
                .append(getOvertime());
        return builder.toString();
    }

    private String getSuccessfulNotification() {
        return "打刻が完了しました。";
    }

    private String getAttendanceDate() {
        return "出勤日：" + this.attendanceRecord.getAttendanceLocalDate();
    }

    private String getAttendanceStartEnd() {
        return String.format(
                "%s - %s",
                this.attendanceRecord.getAttendanceStartLocalTime(),
                this.attendanceRecord.getAttendanceEndLocalTime()
        );
    }

    private String getWorkingTime() {
        return "労働時間：" + String.format("%s時間%s分",
                                       this.attendanceRecord.getActualMinutes().convertActualWorkingTimeMinutesToHour(),
                                       this.attendanceRecord.getActualMinutes().extractRemainderActualWorkingTimeMinutes());
    }
    private String getOvertime() {
        return "残業時間：" + String.format("%s時間%s分",
                                       this.attendanceRecord.getActualMinutes().convertActualOvertimeMinutesToHour(),
                                       this.attendanceRecord.getActualMinutes().extractRemainderActualOvertimeMinutes());
    }
}
