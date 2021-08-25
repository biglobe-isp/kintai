package com.naosim.dddwork.kintai.domain.timerecord;

import com.naosim.dddwork.kintai.domain.timerecord.attendance.AttendanceDate;
import com.naosim.dddwork.kintai.domain.timerecord.overtime.ActualOvertimeMinutes;
import com.naosim.dddwork.kintai.domain.timerecord.workingtime.ActualWorkingTimeMinutes;
import com.naosim.dddwork.kintai.domain.timerecord.attendance.AttendanceTimeInterval;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Value;

import java.time.LocalDate;
import java.time.LocalTime;

@Value
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class AttendanceRecord {
    @NonNull
    LocalDate ymd;
    // TODO: 名前考える
    @NonNull
    LocalTime startTime;
    @NonNull
    LocalTime endTime;
    int workingTimeMinutes;
    int overtimeMinutes;

    public AttendanceRecord(AttendanceDate date, AttendanceTimeInterval interval,
                               ActualWorkingTimeMinutes workingTimeMinutes, ActualOvertimeMinutes overtimeMinutes) {
        this.ymd = date.getZonedDateTime().toLocalDate();
        this.startTime = interval.getStartTimePoint().getZonedDateTime().toLocalTime();
        this.endTime = interval.getEndTimePoint().getZonedDateTime().toLocalTime();
        this.workingTimeMinutes = workingTimeMinutes.intValue();
        this.overtimeMinutes = overtimeMinutes.intValue();
    }
}
