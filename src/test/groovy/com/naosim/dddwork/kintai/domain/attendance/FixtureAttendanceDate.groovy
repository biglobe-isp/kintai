package com.naosim.dddwork.kintai.domain.attendance

import com.naosim.dddwork.kintai.domain.timerecord.attendance.AttendanceDate

import java.time.LocalDate

class FixtureAttendanceDate {
    static AttendanceDate get() {
        new AttendanceDate(LocalDate.of(2021, 8, 26))
    }
}
