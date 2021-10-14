package kintai.domain

import java.time.LocalDateTime

class FixtureAttendanceTime {
    static AttendanceTime getEndBeforeLunchBreak() {
        new AttendanceTime(
                LocalDateTime.of(2021,10,1,9,0,0),
                LocalDateTime.of(2021,10,1,11,0,0),)
    }

    static AttendanceTime getEndBetweenLunchBreak() {
        new AttendanceTime(
                LocalDateTime.of(2021,10,1,9,0,0),
                LocalDateTime.of(2021,10,1,12,30,0),)
    }

    static AttendanceTime getEndBeforeNightBreak() {
        new AttendanceTime(
                LocalDateTime.of(2021,10,1,9,0,0),
                LocalDateTime.of(2021,10,1,17,0,0),)
    }

    static AttendanceTime getEndBetweenNightBreak() {
        new AttendanceTime(
                LocalDateTime.of(2021,10,1,9,0,0),
                LocalDateTime.of(2021,10,1,18,30,0),)
    }

    static AttendanceTime getEndBeforeMidNightBreak() {
        new AttendanceTime(
                LocalDateTime.of(2021,10,1,9,0,0),
                LocalDateTime.of(2021,10,1,20,0,0),)
    }

    static AttendanceTime getEndBetweenMidNightBreak() {
        new AttendanceTime(
                LocalDateTime.of(2021,10,1,9,0,0),
                LocalDateTime.of(2021,10,1,21,30,0),)
    }

    static AttendanceTime getEndAfterMidNightBreak() {
        new AttendanceTime(
                LocalDateTime.of(2021,10,1,9,0,0),
                LocalDateTime.of(2021,10,1,23,0,0),)
    }

}
