package kintai.api

import kintai.domain.FixtureAttendance
import kintai.service.AttendanceInputRequest
import kintai.service.AttendanceService
import kintai.service.AttendanceTotalResponse
import spock.lang.Specification

class AttendanceApiSpec extends Specification {
    private AttendanceService attendanceService = Mock()

    private AttendanceApi attendanceApi = new AttendanceApi(attendanceService)

    def "勤怠入力"() {
        setup:
        def attendance = FixtureAttendance.getAttendance1()
        def request = new AttendanceInputRequest(
                attendance.getAttendanceDate().format(),
                attendance.getAttendanceTime().formatFrom().substring(8,12),
                attendance.getAttendanceTime().formatTo().substring(8,12))

        when:
        attendanceApi.input(request)

        then:
        1 * attendanceService.input(request)
    }

    def "勤怠集計"() {
        setup:
        def yearMonth = "202110"
        def expected = new AttendanceTotalResponse(2000,100)
        attendanceService.total(yearMonth) >> expected

        when:
        def result = attendanceApi.total(yearMonth)

        then:
        1 * attendanceService.total(yearMonth) >> expected
        result == expected
    }
}
