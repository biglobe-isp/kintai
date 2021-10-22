package kintai.service

import kintai.domain.AttendanceRepository
import kintai.domain.FixtureAttendance
import spock.lang.Specification

import java.time.YearMonth

class AttendanceServiceSpec extends Specification {

    private AttendanceRepository attendanceRepository = Mock()

    private AttendanceService attendanceService = new AttendanceService(attendanceRepository)

    def "勤怠入力"() {
        setup:
        def attendance = FixtureAttendance.getAttendance1()
        def request = new AttendanceInputRequest(
                attendance.getAttendanceDate().format(),
                attendance.getAttendanceTime().formatFrom().substring(8,12),
                attendance.getAttendanceTime().formatTo().substring(8,12))
        when:
        attendanceService.input(request)

        then:
        1 * attendanceRepository.persist(attendance)
    }

    def "勤怠集計"() {
        setup:
        def yearMonth = YearMonth.of(2021,10)
        def attendance1 = FixtureAttendance.getAttendance1()
        def attendance2 = FixtureAttendance.getAttendance2()
        def attendance3 = FixtureAttendance.getAttendance3()
        attendanceRepository.select(yearMonth) >> List.of(attendance1,attendance2,attendance3)
        def expectedTotalWorkMinutes =
                attendance1.getWorkDuration().getDuration().toMinutes() + attendance2.getWorkDuration().getDuration().toMinutes() + attendance3.getWorkDuration().getDuration().toMinutes()
        def expectedTotalOverWorkMinutes =
                attendance1.getOverWorkDuration().getDuration().toMinutes() + attendance2.getOverWorkDuration().getDuration().toMinutes() + attendance3.getOverWorkDuration().getDuration().toMinutes()

        when:
        def result = attendanceService.total("202110")

        then:
        1 * attendanceRepository.select(yearMonth)  >> List.of(attendance1,attendance2,attendance3)
        expectedTotalWorkMinutes == result.getTotalWorkMinutes()
        expectedTotalOverWorkMinutes == result.getTotalOverWorkMinutes()
    }
}
