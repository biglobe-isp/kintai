package kintai.domain

import java.time.LocalDate
import java.time.LocalDateTime

class FixtureAttendance {

    static Attendance getAttendance1() {

        new Attendance(
                FixtureAttendanceDate.get(),
                FixtureAttendanceTime.getEndBetweenNightBreak(),
                FixtureWorkDuration.getFullTime(),
                FixtureOverWorkDuration.get())
    }

    static Attendance getAttendance2() {

        new Attendance(
                FixtureAttendanceDate.get(),
                FixtureAttendanceTime.getEndAfterMidNightBreak(),
                FixtureWorkDuration.getOverTime(),
                FixtureOverWorkDuration.get())
    }

    static Attendance getAttendance3() {

        new Attendance(
                FixtureAttendanceDate.get(),
                FixtureAttendanceTime.getEndBeforeLunchBreak(),
                FixtureWorkDuration.getShortTime(),
                FixtureOverWorkDuration.get())
    }

}
