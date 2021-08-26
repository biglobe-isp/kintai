package com.naosim.dddwork.kintai.domain.timerecord;

import com.naosim.dddwork.kintai.domain.timerecord.attendance.AttendanceDate;
import com.naosim.dddwork.kintai.domain.timerecord.attendance.AttendanceTimeInterval;
import com.naosim.dddwork.kintai.domain.timerecord.overtime.ActualOvertimeMinutes;
import com.naosim.dddwork.kintai.domain.timerecord.workingtime.ActualWorkingTimeMinutes;
import lombok.NonNull;
import lombok.Value;

import java.time.LocalDate;
import java.time.LocalTime;

@Value
public class AttendanceRecord {
    @NonNull
    AttendanceDate attendanceDate;
    @NonNull
    AttendanceTimeInterval attendanceTimeInterval;
    @NonNull
    ActualWorkingTimeMinutes actualWorkingTimeMinutes;
    @NonNull
    ActualOvertimeMinutes actualOvertimeMinutes;

    public LocalDate getAttendanceLocalDate() {
        return this.attendanceDate.getZonedDateTime().toLocalDate();
    }

    public LocalTime getAttendanceStartLocalTime() {
        return this.attendanceTimeInterval.getInterval().getStartTimePoint().getZonedDateTime().toLocalTime();
    }

    public LocalTime getAttendanceEndLocalTime() {
        return this.attendanceTimeInterval.getInterval().getEndTimePoint().getZonedDateTime().toLocalTime();
    }
}
