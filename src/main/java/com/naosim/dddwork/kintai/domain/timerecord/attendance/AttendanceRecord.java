package com.naosim.dddwork.kintai.domain.timerecord.attendance;

import com.naosim.dddwork.kintai.domain.timerecord.actualtime.ActualMinutes;
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
    ActualMinutes actualMinutes;

    public LocalDate getAttendanceLocalDate() {
        return this.attendanceDate.getZonedDateTime().toLocalDate();
    }

    public LocalTime getAttendanceStartLocalTime() {
        return this.attendanceTimeInterval.getInterval().getStartTimePoint().getZonedDateTime().toLocalTime();
    }

    public LocalTime getAttendanceEndLocalTime() {
        return this.attendanceTimeInterval.getInterval().getEndTimePoint().getZonedDateTime().toLocalTime();
    }

    public int getActualWorkingTimeMinutesLength() {
        return this.actualMinutes.getActualWorkingTimeMinutes().intValue();
    }

    public int getActualOvertimeMinutesLength() {
        return this.actualMinutes.getActualOvertimeMinutes().intValue();
    }
}
