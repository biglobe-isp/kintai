package com.naosim.dddwork.datasource

import com.naosim.dddwork.domain.date.WorkingDate
import com.naosim.dddwork.domain.date.Year
import com.naosim.dddwork.domain.date.Month
import com.naosim.dddwork.domain.date.Day
import com.naosim.dddwork.domain.time.WorkingDuration
import com.naosim.dddwork.service.AttendanceRecordUpdateService

import spock.lang.Specification

class AttendanceRecordUpdateServiceSpec extends Specification {

    def "AttendanceRecordUpdateService-Normal" () {
        setup:
        def attendanceRecordUpdateService = new AttendanceRecordUpdateService("20190402","0900","1800");

        when:
        def attendanceRecordRepositoryCSV = new AttendanceRecordRepositoryCSV()
        TreeMap<WorkingDate, WorkingDuration> attendanceRecords = attendanceRecordRepositoryCSV.load()
        def workingDate = new WorkingDate(new Year(2019), new Month(4) , new Day(2))
        def workingDuration = attendanceRecords[workingDate]

        then:
        workingDuration.toString() == "0900-1800"

    }

    def "AttendanceRecordUpdateService-Overtime1" () {
        setup:
        def attendanceRecordUpdateService = new AttendanceRecordUpdateService("20190402","0900","2530");

        when:
        def attendanceRecordRepositoryCSV = new AttendanceRecordRepositoryCSV()
        TreeMap<WorkingDate, WorkingDuration> attendanceRecords = attendanceRecordRepositoryCSV.load()
        def workingDate = new WorkingDate(new Year(2019), new Month(4) , new Day(2))
        def workingDuration = attendanceRecords[workingDate]

        then:
        workingDuration.toString() == "0900-2400"

    }

    def "AttendanceRecordUpdateService-Overtime2" () {
        setup:
        def attendanceRecordUpdateService = new AttendanceRecordUpdateService("20190402","0900","0200");

        when:
        def attendanceRecordRepositoryCSV = new AttendanceRecordRepositoryCSV()
        TreeMap<WorkingDate, WorkingDuration> attendanceRecords = attendanceRecordRepositoryCSV.load()
        def workingDate = new WorkingDate(new Year(2019), new Month(4) , new Day(2))
        def workingDuration = attendanceRecords[workingDate]

        then:
        workingDuration.toString() == "0900-2400"

    }

    def "AttendanceRecordUpdateService-MultiEntry" () {
        setup:
        def attendanceRecordUpdateService1 = new AttendanceRecordUpdateService("20190401","0900","1800");
        def attendanceRecordUpdateService2 = new AttendanceRecordUpdateService("20190402","0900","2500");
        def attendanceRecordUpdateService3 = new AttendanceRecordUpdateService("20190403","0900","0200");

        when:
        def attendanceRecordRepositoryCSV = new AttendanceRecordRepositoryCSV()
        TreeMap<WorkingDate, WorkingDuration> attendanceRecords = attendanceRecordRepositoryCSV.load()
        def workingDate1 = new WorkingDate(new Year(2019), new Month(4) , new Day(1))
        def workingDate2 = new WorkingDate(new Year(2019), new Month(4) , new Day(2))
        def workingDate3 = new WorkingDate(new Year(2019), new Month(4) , new Day(3))
        def workingDuration1 = attendanceRecords[workingDate1]
        def workingDuration2 = attendanceRecords[workingDate2]
        def workingDuration3 = attendanceRecords[workingDate3]

        then:
        workingDuration1.toString() == "0900-1800"
        workingDuration2.toString() == "0900-2400"
        workingDuration3.toString() == "0900-2400"
    }
}
