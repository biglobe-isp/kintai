package kintai.datasource

import kintai.domain.FixtureAttendance
import spock.lang.Specification

import java.time.YearMonth

class AttendanceRepositoryCsvSpec extends Specification{

    private AttendanceMapperCsv attendanceMapperCsv = Mock()

    private AttendanceRepositoryCsv repository = new AttendanceRepositoryCsv(attendanceMapperCsv)

    def "勤怠情報を登録する"() {
        setup:
        def attendance = FixtureAttendance.getAttendance1()

        when:
        repository.persist(attendance)

        then:
        1 * attendanceMapperCsv.save("data.csv",attendance)
    }

    def "勤怠情報をリストで取得する"() {
        setup:
        def attendance = FixtureAttendance.getAttendance1()
        def yearMonth = YearMonth.of(2021,10)
        attendanceMapperCsv.findByYearMonth("data.csv",yearMonth) >> List.of(attendance,attendance,attendance)

        when:
        def result = repository.select(yearMonth)

        then:
        1 * attendanceMapperCsv.findByYearMonth("data.csv",yearMonth) >> List.of(attendance,attendance,attendance)
        result == List.of(attendance,attendance,attendance)
    }
}
