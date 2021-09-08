package com.naosim.dddwork.kintai.domain.attendance

import com.naosim.dddwork.kintai.domain.timerecord.attendance.AttendanceDate

class FixtureAttendanceDate {
    static AttendanceDate get() {
        new AttendanceDate("20210826")
    }
}
