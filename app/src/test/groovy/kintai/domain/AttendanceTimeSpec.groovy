package kintai.domain

import spock.lang.Specification

import java.time.Duration

class AttendanceTimeSpec extends Specification {
    def "出社時間文字列を成形する"() {
        setup:
        def attendanceTime = FixtureAttendanceTime.getEndBetweenNightBreak();

        when:
        def start = attendanceTime.formatFrom()

        then:
        start == "202110010900"
    }

    def "退勤時間文字列を成形する"() {
        setup:
        def attendanceTime = FixtureAttendanceTime.getEndBetweenNightBreak();

        when:
        def start = attendanceTime.formatTo()

        then:
        start == "202110011830"
    }

    def "労働時間を生成する・昼休憩開始前に退勤"() {
        setup:
        def attendanceTime = FixtureAttendanceTime.getEndBeforeLunchBreak();

        when:
        def workDuration = attendanceTime.calculateWorkDuration()

        then:
        workDuration == new WorkDuration(Duration.ofHours(2))
    }

    def "労働時間を生成する・昼休憩の間に退勤"() {
        setup:
        def attendanceTime = FixtureAttendanceTime.getEndBetweenLunchBreak()

        when:
        def workDuration = attendanceTime.calculateWorkDuration()

        then:
        workDuration == new WorkDuration(Duration.ofHours(3))
    }

    def "労働時間を生成する・夜休憩開始前に退勤"() {
        setup:
        def attendanceTime = FixtureAttendanceTime.getEndBeforeNightBreak();

        when:
        def workDuration = attendanceTime.calculateWorkDuration()

        then:
        workDuration == new WorkDuration(Duration.ofHours(7))
    }

    def "労働時間を生成する・夜休憩の間に退勤"() {
        setup:
        def attendanceTime = FixtureAttendanceTime.getEndBetweenNightBreak();

        when:
        def workDuration = attendanceTime.calculateWorkDuration()

        then:
        workDuration == new WorkDuration(Duration.ofHours(8))
    }

    def "労働時間を生成する・深夜休憩開始前に退勤"() {
        setup:
        def attendanceTime = FixtureAttendanceTime.getEndBeforeMidNightBreak();

        when:
        def workDuration = attendanceTime.calculateWorkDuration()

        then:
        workDuration == new WorkDuration(Duration.ofHours(9))
    }

    def "労働時間を生成する・深夜休憩の間に退勤"() {
        setup:
        def attendanceTime = FixtureAttendanceTime.getEndBetweenMidNightBreak();

        when:
        def workDuration = attendanceTime.calculateWorkDuration()

        then:
        workDuration == new WorkDuration(Duration.ofHours(10))
    }

    def "労働時間を生成する・深夜休憩後に退勤"() {
        setup:
        def attendanceTime = FixtureAttendanceTime.getEndAfterMidNightBreak();

        when:
        def workDuration = attendanceTime.calculateWorkDuration()

        then:
        workDuration == new WorkDuration(Duration.ofHours(11))
    }
}
