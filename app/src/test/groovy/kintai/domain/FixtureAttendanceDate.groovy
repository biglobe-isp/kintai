package kintai.domain

import java.time.LocalDate

class FixtureAttendanceDate {
    static AttendanceDate get() {
        new AttendanceDate(LocalDate.of(2021,10,01))
    }
}
