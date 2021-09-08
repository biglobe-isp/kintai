package com.naosim.dddwork.kintai.domain.attendance


import com.naosim.dddwork.kintai.domain.WrappedTimeInterval
import com.naosim.dddwork.kintai.domain.timerecord.attendance.AttendanceTimeInterval

class WrappedAttendanceTimeInterval {
    static AttendanceTimeInterval get(startTime, endTime) {
        new AttendanceTimeInterval(WrappedTimeInterval.get(startTime, endTime))
    }
}
