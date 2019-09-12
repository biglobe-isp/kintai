package kintai.service

import kintai.domain.Attendance

import kintai.domain.AttendanceStartAndEndTime
import kintai.domain.Attendances
import kintai.domain.BreakRanges
import kintai.domain.EmployeeRule
import kintai.domain.EndTime
import kintai.domain.StartTime
import kintai.domain.TotalWorkingTime
import kintai.domain.WorkingDuration
import spock.lang.Specification

import kintai.domain.AttendanceRepository

import java.time.Duration
import java.time.LocalDate
import java.time.LocalDateTime

class AttendanceServiceSpec extends Specification {

    def employeeRule = EmployeeRule.defaultEmployeeRule()

    def "勤怠を記録する"() {
        setup:
        def repository = Mock(AttendanceRepository)
        def service = new AttendanceService(repository)

        def input = new AttendanceStartAndEndTime(LocalDate.of(2019, 9, 2),
                StartTime.of(9, 0), EndTime.of(20, 0))
        def now = LocalDateTime.now()

        def expected = new Attendance(input,
                WorkingDuration.ofMinutes(540), WorkingDuration.ofMinutes(60), now)

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

        def attendances = new Attendances(
                new Attendance(LocalDate.of(2019, 9, 2), StartTime.of(9, 0), EndTime.of(16,0), WorkingDuration.ofMinutes(480), WorkingDuration.ZERO, LocalDateTime.now()),
                new Attendance(LocalDate.of(2019, 9, 3), StartTime.of(9, 0), EndTime.of(16,0), WorkingDuration.ofMinutes(540), WorkingDuration.ofMinutes(60), LocalDateTime.now())
        )
        def expected = new TotalWorkingTime(WorkingDuration.ofMinutes(1020), WorkingDuration.ofMinutes(60))

        when:
        def result = service.total()

        then:
        1 * repository.findAttendancesByMonth(_) >> attendances
        result == expected
    }
}
