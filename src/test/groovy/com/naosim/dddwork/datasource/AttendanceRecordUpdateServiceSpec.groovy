package com.naosim.dddwork.datasource

import com.naosim.dddwork.domain.AttendanceRecord
import com.naosim.dddwork.domain.date.WorkingDate
import com.naosim.dddwork.domain.date.Year
import com.naosim.dddwork.domain.date.Month
import com.naosim.dddwork.domain.date.Day
import com.naosim.dddwork.domain.time.EntryTime
import com.naosim.dddwork.domain.time.Hour
import com.naosim.dddwork.domain.time.Minute
import com.naosim.dddwork.domain.time.WorkingDuration
import com.naosim.dddwork.service.AttendanceRecordUpdateService

import spock.lang.Specification

class AttendanceRecordUpdateServiceSpec extends Specification {

    def "AttendanceRecordUpdateService-Normal" () {
        setup:
        def attendanceRecordRepositoryCSV = new AttendanceRecordRepositoryCSV()
        attendanceRecordRepositoryCSV.delete()
        def attendanceRecordUpdateService = new AttendanceRecordUpdateService()
        def year = new Year(2019)
        def month = new Month(4)
        def day = new Day(1)
        def workingDate = new WorkingDate(year,month,day)
        def startHour = new Hour(9)
        def startMinutes = new Minute(0)
        def endHour = new Hour(18)
        def endMinutes = new Minute(0)
        def startTime = new EntryTime(startHour,startMinutes)
        def endTime = new EntryTime(endHour,endMinutes)
        def workingDuration = new WorkingDuration(startTime,endTime)
        def attendanceRecord = new AttendanceRecord(workingDate,workingDuration)
        attendanceRecordUpdateService.executeService(attendanceRecord)

        when:
        def attendanceRecords = attendanceRecordRepositoryCSV.load().getAttendanceRecords()
        def attendanceRecordResult = attendanceRecords.get(0)

        then:
        attendanceRecordResult.toString() == "20190401 09:00-18:00"

    }

    /*
    def "AttendanceRecordUpdateService-Overtime1" () {
        setup:
        def attendanceRecordUpdateService = new AttendanceRecordUpdateService();
        attendanceRecordUpdateService.executeService("20190402","0900","2530")

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
        def attendanceRecordUpdateService = new AttendanceRecordUpdateService()
        attendanceRecordUpdateService.executeService("20190402","0900","0200")

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
        def attendanceRecordUpdateService = new AttendanceRecordUpdateService();

        attendanceRecordUpdateService.executeService("20190401","0900","1800");
        attendanceRecordUpdateService.executeService("20190402","0900","2500");
        attendanceRecordUpdateService.executeService("20190403","0900","0200");

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

     */
}
