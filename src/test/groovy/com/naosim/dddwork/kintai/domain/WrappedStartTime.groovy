package com.naosim.dddwork.kintai.domain

import com.naosim.dddwork.kintai.domain.attendance.FixtureAttendanceDate
import com.naosim.dddwork.kintai.domain.timerecord.StartTime

import java.time.LocalTime
import java.time.format.DateTimeFormatter

class WrappedStartTime {
    static StartTime get(startTime) {
        new StartTime(FixtureAttendanceDate.get().toLocalDate(), LocalTime.parse(startTime, DateTimeFormatter.ofPattern("HHmm")))
    }
}
