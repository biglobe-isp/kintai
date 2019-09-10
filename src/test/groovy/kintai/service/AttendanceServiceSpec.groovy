package kintai.service

import kintai.domain.Attendance

import kintai.domain.AttendanceStartAndEndTime
import kintai.domain.Attendances
import kintai.domain.BreakTimes
import kintai.domain.EmployeeRule
import kintai.domain.TotalWorkingTime
import kintai.domain.WorkingRange
import spock.lang.Specification

import kintai.domain.AttendanceRepository

import java.time.Duration
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

class AttendanceServiceSpec extends Specification {

    def breakTimes = new BreakTimes(Arrays.asList(
            new WorkingRange(LocalTime.of(12, 0), LocalTime.of(13, 0)),
            new WorkingRange(LocalTime.of(18, 0), LocalTime.of(19, 0)),
            new WorkingRange(LocalTime.of(21, 0), LocalTime.of(22, 0))
    ));

    def employeeRule = new EmployeeRule(breakTimes, Duration.ofHours(8))

    def "勤怠を記録する"() {
        setup:
        def repository = Mock(AttendanceRepository)
        def service = new AttendanceService(repository)

        def attendanceDate = LocalDate.of(2019, 9, 2)
        def startTime = LocalTime.of(9, 0);
        def endTime = LocalTime.of(20, 0);
        def input = new AttendanceStartAndEndTime(attendanceDate, startTime, endTime)
        def now = LocalDateTime.now()

        def workingDuration = Duration.ofHours(9)
        def overWorkingDuration = Duration.ofHours(1)
        def expected = new Attendance(attendanceDate, startTime, endTime, workingDuration, overWorkingDuration, now)

        when:
        def result = service.input(input, employeeRule, now)

        then:
        1 * repository.saveAttendance(_)
        result == expected
    }

    def "勤務時間を合計する"() {
        setup:
        def repository = Mock(AttendanceRepository)
        def service = new AttendanceService(repository)

        def attendances = new Attendances(Arrays.asList(
                new Attendance(LocalDate.of(2019, 9, 2), LocalTime.of(9, 0), LocalTime.of(16,0), Duration.ofHours(8), Duration.ZERO, LocalDateTime.now()),
                new Attendance(LocalDate.of(2019, 9, 3), LocalTime.of(9, 0), LocalTime.of(16,0), Duration.ofHours(9), Duration.ofHours(1), LocalDateTime.now())
        ))
        def expected = new TotalWorkingTime(Duration.ofHours(17), Duration.ofHours(1))

        when:
        def result = service.total()

        then:
        1 * repository.findAttendancesByMonth(_) >> attendances
        result == expected
    }
}
