package kintai.domain

import java.time.LocalDate
import java.time.LocalDateTime

class FixtureAttendance {

    static Attendance get() {
        LocalDateTime start = FixtureAttendanceTime.getEndBetweenNightBreak().getStart()

        new Attendance(
                LocalDate.of(start.getYear(),start.getMonth(),start.getDayOfMonth()),
                FixtureAttendanceTime.getEndBetweenNightBreak(),
                FixtureWorkDuration.getFullTime(),
                FixtureOverWorkDuration.get())
    }

}
