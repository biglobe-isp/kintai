package kintai.datasource

import kintai.domain.FixtureAttendance
import spock.lang.Specification

class AttendanceMapperCsvImplSpec extends Specification {

    //テスト対象のMapper
    private AttendanceMapperCsvImpl attendanceMapperCsv = new AttendanceMapperCsvImpl()

    def "CSVに勤怠情報を登録"() {
        setup:
        def attendance = FixtureAttendance.get()

        when:
        attendanceMapperCsv.save(attendance)

        then:
        noExceptionThrown()
    }
}