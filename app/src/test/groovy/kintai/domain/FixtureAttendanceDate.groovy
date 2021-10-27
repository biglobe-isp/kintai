package kintai.domain

import java.time.LocalDate

class FixtureAttendanceDate {
    static AttendanceDate getAttendanceDate1() {
        new AttendanceDate(LocalDate.of(2021,10,01))
    }

    static AttendanceDate getAttendanceDate2() {
        new AttendanceDate(LocalDate.of(2021,10,02))
    }

    static AttendanceDate getAttendanceDate3() {
        new AttendanceDate(LocalDate.of(2021,10,03))
    }
}
