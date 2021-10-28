package kintai.domain

import spock.lang.Specification

import java.time.LocalDate

class AttendanceDateSpec extends Specification {
    def "成形した出勤日文字列を出力する"() {
        setup:
        def attendanceDate = new AttendanceDate(LocalDate.of(2021,10,01))

        when:
        def result = attendanceDate.format()

        then:
        result == "20211001"
    }

}
