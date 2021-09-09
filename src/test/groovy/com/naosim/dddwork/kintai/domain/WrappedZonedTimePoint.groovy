package com.naosim.dddwork.kintai.domain

import com.naosim.dddwork.kintai.domain.attendance.FixtureAttendanceDate
import com.naosim.dddwork.kintai.domain.timerecord.ZonedTimePoint

import java.time.LocalTime
import java.time.format.DateTimeFormatter

class WrappedZonedTimePoint {
    static ZonedTimePoint get(hhmm) {
        new ZonedTimePoint(FixtureAttendanceDate.get().getZonedDateTime(), LocalTime.parse(hhmm, DateTimeFormatter.ofPattern("HHmm")))
    }
}
