package kintai.domain

import spock.lang.Specification

import java.time.Duration

class WorkDurationSpec extends Specification {
    def "労働時間を生成する・昼休憩開始前に退勤"() {
        setup:
        def attendanceTime = FixtureAttendanceTime.getEndBeforeLunchBreak();

        when:
        def workDuration = new WorkDuration(attendanceTime)

        then:
        workDuration.getDuration() == Duration.ofHours(2)
    }

    def "労働時間を生成する・昼休憩の間に退勤"() {
        setup:
        def attendanceTime = FixtureAttendanceTime.getEndBetweenLunchBreak()

        when:
        def workDuration = new WorkDuration(attendanceTime)

        then:
        workDuration.getDuration() == Duration.ofHours(3)
    }

    def "労働時間を生成する・夜休憩開始前に退勤"() {
        setup:
        def attendanceTime = FixtureAttendanceTime.getEndBeforeNightBreak();

        when:
        def workDuration = new WorkDuration(attendanceTime)

        then:
        workDuration.getDuration() == Duration.ofHours(7)
    }

    def "労働時間を生成する・夜休憩の間に退勤"() {
        setup:
        def attendanceTime = FixtureAttendanceTime.getEndBetweenNightBreak();

        when:
        def workDuration = new WorkDuration(attendanceTime)

        then:
        workDuration.getDuration() == Duration.ofHours(8)
    }

    def "労働時間を生成する・深夜休憩開始前に退勤"() {
        setup:
        def attendanceTime = FixtureAttendanceTime.getEndBeforeMidNightBreak();

        when:
        def workDuration = new WorkDuration(attendanceTime)

        then:
        workDuration.getDuration() == Duration.ofHours(9)
    }

    def "労働時間を生成する・深夜休憩の間に退勤"() {
        setup:
        def attendanceTime = FixtureAttendanceTime.getEndBetweenMidNightBreak();

        when:
        def workDuration = new WorkDuration(attendanceTime)

        then:
        workDuration.getDuration() == Duration.ofHours(10)
    }

    def "労働時間を生成する・深夜休憩後に退勤"() {
        setup:
        def attendanceTime = FixtureAttendanceTime.getEndAfterMidNightBreak();

        when:
        def workDuration = new WorkDuration(attendanceTime)

        then:
        workDuration.getDuration() == Duration.ofHours(11)
    }
}
