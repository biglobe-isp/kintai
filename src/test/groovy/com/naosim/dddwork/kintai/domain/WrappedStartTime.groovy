package com.naosim.dddwork.kintai.domain

import com.naosim.dddwork.kintai.domain.attendance.FixtureAttendanceDate
import com.naosim.dddwork.kintai.domain.timerecord.StartTime

class WrappedStartTime {
    static StartTime get(startTime) {
        new StartTime(FixtureAttendanceDate.get(), startTime)
    }
}
