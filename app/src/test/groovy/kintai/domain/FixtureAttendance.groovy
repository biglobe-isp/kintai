package kintai.domain

import java.time.LocalDate
import java.time.LocalDateTime

class FixtureAttendance {

    static Attendance getAttendance1() {
        LocalDateTime start = FixtureAttendanceTime.getEndBetweenNightBreak().getStart()

        new Attendance(
                LocalDate.of(start.getYear(),start.getMonth(),start.getDayOfMonth()),
                FixtureAttendanceTime.getEndBetweenNightBreak(),
                FixtureWorkDuration.getFullTime(),
                FixtureOverWorkDuration.get())
    }

    static Attendance getAttendance2() {
        LocalDateTime start = FixtureAttendanceTime.getEndAfterMidNightBreak().getStart()

        new Attendance(
                LocalDate.of(start.getYear(),start.getMonth(),start.getDayOfMonth()),
                FixtureAttendanceTime.getEndAfterMidNightBreak(),
                FixtureWorkDuration.getOverTime(),
                FixtureOverWorkDuration.get())
    }

    static Attendance getAttendance3() {
        LocalDateTime start = FixtureAttendanceTime.getEndBeforeLunchBreak().getStart()

        new Attendance(
                LocalDate.of(start.getYear(),start.getMonth(),start.getDayOfMonth()),
                FixtureAttendanceTime.getEndBeforeLunchBreak(),
                FixtureWorkDuration.getShortTime(),
                FixtureOverWorkDuration.get())
    }

}
