package kintai.datasource

import kintai.domain.FixtureAttendance
import spock.lang.Specification

class AttendanceRepositoryCsvSpec extends Specification{

    private AttendanceMapperCsv attendanceMapperCsv = Mock()

    private AttendanceRepositoryCsv repository = new AttendanceRepositoryCsv(attendanceMapperCsv)

    def "勤怠情報を登録する"() {
        setup:
        def attendance = FixtureAttendance.get()

        when:
        repository.persist(attendance)

        then:
        1 * attendanceMapperCsv.save(attendance)
    }

    def "勤怠情報を更新する"() {
        setup:
        def attendance = FixtureAttendance.get()

        when:
        repository.update(attendance)

        then:
        1 * attendanceMapperCsv.update(attendance)
    }

    def "勤怠情報の存在確認をする"() {
        setup:
        def attendance = FixtureAttendance.get()
        attendanceMapperCsv.findByDay(attendance.getDate()) >> Optional.of(attendance)

        when:
        def result = repository.exists(attendance.getDate())

        then:
        1 * attendanceMapperCsv.findByDay(attendance.getDate()) >> Optional.of(attendance)
        result
    }

    def "勤怠情報の存在確認をする・勤怠情報が存在しない"() {
        setup:
        def attendance = FixtureAttendance.get()
        attendanceMapperCsv.findByDay(attendance.getDate()) >> Optional.empty()

        when:
        def result = repository.exists(attendance.getDate())

        then:
        1 * attendanceMapperCsv.findByDay(attendance.getDate()) >> Optional.empty()
        !result
    }

    def "勤怠情報をリストで取得する"() {
        setup:
        def attendance = FixtureAttendance.get()
        attendanceMapperCsv.findByYearMonth(202110) >> List.of(attendance,attendance,attendance)

        when:
        def result = repository.select(202110)

        then:
        1 * attendanceMapperCsv.findByYearMonth(202110) >> List.of(attendance,attendance,attendance)
        result == List.of(attendance,attendance,attendance)
    }
}
