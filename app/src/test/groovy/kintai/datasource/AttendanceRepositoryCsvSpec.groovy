package kintai.datasource

import kintai.domain.FixtureAttendance
import spock.lang.Specification

import java.nio.file.Paths
import java.time.YearMonth

class AttendanceRepositoryCsvSpec extends Specification{

    private AttendanceMapperCsv attendanceMapperCsv = Mock()

    private AttendanceRepositoryCsv repository = new AttendanceRepositoryCsv(attendanceMapperCsv,Paths.get("data.csv"))

    def "勤怠情報を登録する"() {
        setup:
        def attendance = FixtureAttendance.getAttendance1()

        when:
        repository.persist(attendance)

        then:
        1 * attendanceMapperCsv.save(Paths.get("data.csv"),attendance)
    }

    def "勤怠情報をリストで取得する"() {
        setup:
        def attendance = FixtureAttendance.getAttendance1()
        def yearMonth = YearMonth.of(2021,10)
        attendanceMapperCsv.findByYearMonth(Paths.get("data.csv"),yearMonth) >> List.of(attendance,attendance,attendance)

        when:
        def result = repository.select(yearMonth)

        then:
        1 * attendanceMapperCsv.findByYearMonth(Paths.get("data.csv"),yearMonth) >> List.of(attendance,attendance,attendance)
        result == List.of(attendance,attendance,attendance)
    }
}
