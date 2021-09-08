package com.naosim.dddwork.kintai.domain

import com.naosim.dddwork.kintai.domain.attendance.FixtureAttendanceDate
import com.naosim.dddwork.kintai.domain.timerecord.EndTime

class WrappedEndTime {
    static EndTime get(endTime) {
        new EndTime(FixtureAttendanceDate.get(), endTime)
    }
}
