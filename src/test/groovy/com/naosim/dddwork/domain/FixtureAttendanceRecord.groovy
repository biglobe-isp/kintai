package com.naosim.dddwork.domain

import com.naosim.dddwork.domain.date.Day
import com.naosim.dddwork.domain.date.Month
import com.naosim.dddwork.domain.date.WorkingDate
import com.naosim.dddwork.domain.date.Year
import com.naosim.dddwork.domain.time.EntryTime
import com.naosim.dddwork.domain.time.Hour
import com.naosim.dddwork.domain.time.Minute
import com.naosim.dddwork.domain.time.WorkingDuration

class FixtureAttendanceRecord  {
   static  get(iYear,iMonth,iDay,iStartHour,iStartMin, iEndHour,iEndMin) {
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
}
