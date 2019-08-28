package com.naosim.dddwork.datasource

import com.naosim.dddwork.domain.AttendanceRecord
import com.naosim.dddwork.domain.AttendanceRecords
import com.naosim.dddwork.domain.AttendanceSummary
import com.naosim.dddwork.domain.date.*
import com.naosim.dddwork.domain.time.EntryTime
import com.naosim.dddwork.domain.time.Hour
import com.naosim.dddwork.domain.time.Minute
import com.naosim.dddwork.domain.time.WorkingDuration
import com.naosim.dddwork.service.AttendanceRecordSummaryService
import com.naosim.dddwork.service.AttendanceRecordUpdateService
import spock.lang.Specification

class AttendanceRecordSummaryServiceSpec extends Specification {

    def createAttendanceRecord(iYear,iMonth,iDay,iStartHour,iStartMin, iEndHour,iEndMin) {
        def year = new Year(iYear)
        def month = new Month(iMonth)
        def day = new Day(iDay)
        def workingDate = new WorkingDate(year,month,day)
        def startHour = new Hour(iStartHour)
        def startMin = new Minute(iStartMin)
        def startTime = new EntryTime(startHour,startMin)
        def endHour = new Hour(iEndHour)
        def endMin = new Minute(iEndMin)
        def endTime = new EntryTime(endHour,endMin)
        def workingDuration = new WorkingDuration(startTime,endTime)
        AttendanceRecord attendanceRecord = new AttendanceRecord(workingDate,workingDuration)
        return attendanceRecord
    }

    def "AttendanceSummary-fired-case" () {
        setup:
        def attendanceRecord1 = createAttendanceRecord(2019,5,1,10,0,18,0)
        def attendanceRecords = new AttendanceRecords()
        attendanceRecords.insert(attendanceRecord1)

        when:
        def attendanceSummary = new AttendanceSummary(attendanceRecords)

        then:
        attendanceSummary.isFired() == true
    }

    def "AttendanceSummary-working-hours-calc1-regular" () {
        setup:
        def attendanceRecord1 = createAttendanceRecord(2019,5,1,9,0,18,0)
        def attendanceRecords = new AttendanceRecords()
        attendanceRecords.insert(attendanceRecord1)

        when:
        def attendanceSummary = new AttendanceSummary(attendanceRecords)
        def regularTime = attendanceSummary.getRegularTime()
        def overTime = attendanceSummary.getOverTime()

        then:
        regularTime.toHours() == 8
        overTime.toHours() == 0
    }
    def "AttendanceSummary-working-hours-calc2-overtime1" () {
        setup:
        def attendanceRecord1 = createAttendanceRecord(2019,5,1,9,0,19,30)
        def attendanceRecords = new AttendanceRecords()
        attendanceRecords.insert(attendanceRecord1)

        when:
        def attendanceSummary = new AttendanceSummary(attendanceRecords)
        def regularTime = attendanceSummary.getRegularTime()
        def overTime = attendanceSummary.getOverTime()

        then:
        regularTime.toHours() == 8
        overTime.toMinutes() == 30
    }

    def "AttendanceSummary-working-hours-calc3-overtime-midnight" () {
        setup:
        def attendanceRecord1 = createAttendanceRecord(2019,5,1,9,0,24,0)
        def attendanceRecords = new AttendanceRecords()
        attendanceRecords.insert(attendanceRecord1)

        when:
        def attendanceSummary = new AttendanceSummary(attendanceRecords)
        def regularTime = attendanceSummary.getRegularTime()
        def overTime = attendanceSummary.getOverTime()

        then:
        regularTime.toHours() == 8
        overTime.toHours() == 4
        overTime.toMinutes() == 240
    }

    def "AttendanceRecordSummaryService-TestSimple"() {
        setup:
        def attendanceRecordRepositoryCSV = new AttendanceRecordRepositoryCSV()
        attendanceRecordRepositoryCSV.delete()
        def attendanceRecordUpdateService = new AttendanceRecordUpdateService()
        def attendanceRecordSummaryService = new AttendanceRecordSummaryService()

        when:
        def attendanceRecord1 = createAttendanceRecord(2019,5,1,9,0,18,0)
        def attendanceRecord2 = createAttendanceRecord(2019,5,1,9,0,18,0)
        def attendanceRecord3 = createAttendanceRecord(2019,5,1,9,0,20,0)
        attendanceRecordUpdateService.executeService(attendanceRecord1)
        attendanceRecordUpdateService.executeService(attendanceRecord2)
        attendanceRecordUpdateService.executeService(attendanceRecord3)
        def year = new Year(2019)
        def month = new Month(5)
        def yearMonth =  new YearMonth(year,month)
        def result = attendanceRecordSummaryService.executeService(yearMonth)

        then:
        result.toString() == "24:0/1:0"
    }

    def "AttendanceRecordSummaryService-TestMinutes"() {
        setup:
        def attendanceRecordRepositoryCSV = new AttendanceRecordRepositoryCSV()
        attendanceRecordRepositoryCSV.delete()
        def attendanceRecordUpdateService = new AttendanceRecordUpdateService()
        def attendanceRecordSummaryService = new AttendanceRecordSummaryService()

        when:
        def attendanceRecord1 = createAttendanceRecord(2019,5,1,9,0,17,35)
        def attendanceRecord2 = createAttendanceRecord(2019,5,1,9,0,19,0)
        def attendanceRecord3 = createAttendanceRecord(2019,5,1,9,0,23,55)
        attendanceRecordUpdateService.executeService(attendanceRecord1)
        attendanceRecordUpdateService.executeService(attendanceRecord2)
        attendanceRecordUpdateService.executeService(attendanceRecord3)
        def year = new Year(2019)
        def month = new Month(5)
        def yearMonth =  new YearMonth(year,month)
        def result = attendanceRecordSummaryService.executeService(yearMonth)

        then:
        result.fired == false
        result.toString() == "23:35/3:55"
    }
}
