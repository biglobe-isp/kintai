package com.naosim.dddwork.datasource

import com.naosim.dddwork.service.AttendanceRecordSummaryService
import com.naosim.dddwork.service.AttendanceRecordUpdateService
import spock.lang.Specification

class AttendanceRecordSummaryServiceSpec extends Specification {

    def "AttendanceRecordSummaryService-TestSimple"() {
        setup:
        def attendanceRecordRepositoryCSV = new AttendanceRecordRepositoryCSV()
        attendanceRecordRepositoryCSV.delete()
        def attendanceRecordUpdateService = new AttendanceRecordUpdateService()
        def attendanceRecordSummaryService = new AttendanceRecordSummaryService()

        when:
        attendanceRecordUpdateService.executeService("20190501","0900","1800")
        attendanceRecordUpdateService.executeService("20190502","0900","1900")
        attendanceRecordUpdateService.executeService("20190503","0900","2000")
        def fired = attendanceRecordSummaryService.isEmployeeFired()
        def attendanceSummary = attendanceRecordSummaryService.executeService("2019","05")

        then:
        fired == false
        attendanceSummary.toString() == "24:0/1:0"
    }

    def "AttendanceRecordSummaryService-TestMinutes"() {

        setup:
        def attendanceRecordRepositoryCSV = new AttendanceRecordRepositoryCSV()
        attendanceRecordRepositoryCSV.delete()
        def attendanceRecordUpdateService = new AttendanceRecordUpdateService()
        def attendanceRecordSummaryService = new AttendanceRecordSummaryService()

        when:
        attendanceRecordUpdateService.executeService("20190501","0900","1735")
        attendanceRecordUpdateService.executeService("20190502","0900","1900")
        attendanceRecordUpdateService.executeService("20190503","0900","1955")
        def fired = attendanceRecordSummaryService.isEmployeeFired()
        def attendanceSummary = attendanceRecordSummaryService.executeService("2019","05")

        then:
        fired == false
        attendanceSummary.toString() == "23:35/0:55"
    }
}
