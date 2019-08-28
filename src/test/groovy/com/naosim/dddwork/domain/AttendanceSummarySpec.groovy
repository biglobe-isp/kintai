package com.naosim.dddwork.domain

import com.naosim.dddwork.domain.date.Day
import com.naosim.dddwork.domain.date.Month
import com.naosim.dddwork.domain.date.WorkingDate
import com.naosim.dddwork.domain.date.Year
import com.naosim.dddwork.domain.time.EntryTime
import com.naosim.dddwork.domain.time.Hour
import com.naosim.dddwork.domain.time.Minute
import com.naosim.dddwork.domain.time.WorkingDuration
import spock.lang.Specification

class AttendanceSummarySpec extends Specification{
    def createAttendanceRecord(iYear,iMonth,iDay,iStartHour,iStartMin, iEndHour,iEndMin) {
        def year = new Year(iYear)
        def month = new Month(iMonth)
        def day = new Day(iDay)
        def workingDate = new WorkingDate(year, month, day)
        def startHour = new Hour(iStartHour)
        def startMin = new Minute(iStartMin)
        def startTime = new EntryTime(startHour, startMin)
        def endHour = new Hour(iEndHour)
        def endMin = new Minute(iEndMin)
        def endTime = new EntryTime(endHour, endMin)
        def workingDuration = new WorkingDuration(startTime, endTime)
        AttendanceRecord attendanceRecord = new AttendanceRecord(workingDate, workingDuration)
        return attendanceRecord
    }

    def "AttendanceSummary-fired-case1" () {
        setup:
        def attendanceRecord1 = createAttendanceRecord(2019,5,1,9,1,18,0)
        def attendanceSummary = new AttendanceSummary()

        when:
        def result = attendanceSummary.checkFired(attendanceRecord1)

        then:
        result == true
    }
    def "AttendanceSummary-fired-case2" () {
        setup:
        def attendanceRecord1 = createAttendanceRecord(2019,5,1,8,59,18,0)
        def attendanceSummary = new AttendanceSummary()

        when:
        def result = attendanceSummary.checkFired(attendanceRecord1)

        then:
        result == false
    }

    def "AttendanceSummary-total-working-hours1" () {
        setup:
        def attendanceRecord1 = createAttendanceRecord(2019,5,1,9,0,24,0)
        def attendanceSummary = new AttendanceSummary()

        when:
        def result = attendanceSummary.calculateTotalWorkingHours(attendanceRecord1)

        then:
        result.toHours() == 12
    }

    def "AttendanceSummary-total-working-hours2" () {
        setup:
        def attendanceRecord1 = createAttendanceRecord(2019,5,1,9,0,23,30)
        def attendanceSummary = new AttendanceSummary()

        when:
        def result = attendanceSummary.calculateTotalWorkingHours(attendanceRecord1)

        then:
        result.toHours() == 11
        result.toMinutes() == 11 * 60 + 30
    }

}
