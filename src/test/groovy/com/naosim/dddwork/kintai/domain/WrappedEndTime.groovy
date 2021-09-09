package com.naosim.dddwork.kintai.domain

import com.naosim.dddwork.kintai.domain.attendance.FixtureAttendanceDate
import com.naosim.dddwork.kintai.domain.timerecord.EndTime

import java.time.LocalTime
import java.time.format.DateTimeFormatter

class WrappedEndTime {
    static EndTime get(endTime) {
        new EndTime(FixtureAttendanceDate.get().toLocalDate(), LocalTime.parse(endTime, DateTimeFormatter.ofPattern("HHmm")))
    }
}
