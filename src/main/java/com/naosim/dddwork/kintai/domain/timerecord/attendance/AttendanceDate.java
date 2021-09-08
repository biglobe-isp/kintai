package com.naosim.dddwork.kintai.domain.timerecord.attendance;

import lombok.Value;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@Value
public class AttendanceDate {

    ZonedDateTime zonedDateTime;

    public AttendanceDate(LocalDate attendanceDate) {
        this.zonedDateTime = attendanceDate.atStartOfDay(ZoneId.systemDefault());
    }

    public LocalDate toLocalDate() {
        return this.zonedDateTime.toLocalDate();
    }
}
