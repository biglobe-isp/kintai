package kintai.api

import kintai.domain.FixtureAttendance
import kintai.service.AttendanceInputRequest
import kintai.service.AttendanceService
import kintai.service.AttendanceTotalResponse
import spock.lang.Specification

import java.time.YearMonth

class AttendanceApiSpec extends Specification {
    private AttendanceService attendanceService = Mock()

    private AttendanceApi attendanceApi = new AttendanceApi(attendanceService)

    def "勤怠入力"() {
        setup:
        def attendance = FixtureAttendance.getAttendance1()
        def request = new AttendanceInputRequest(
                attendance.getDate().toString(),
                attendance.getAttendanceTime().getStart().toString(),
                attendance.getAttendanceTime().getEnd().toString())

        when:
        attendanceApi.input(request)

        then:
        1 * attendanceService.input(request)
    }

    def "勤怠集計"() {
        setup:
        def yearMonth = YearMonth.of(2021,10)
        def expected = new AttendanceTotalResponse(2000,100)
        attendanceService.total(yearMonth) >> expected

        when:
        def result = attendanceApi.total(yearMonth)

        then:
        1 * attendanceService.total(yearMonth) >> expected
        result == expected
    }
}
