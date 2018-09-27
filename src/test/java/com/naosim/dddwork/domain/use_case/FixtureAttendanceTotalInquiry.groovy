package com.naosim.dddwork.domain.use_case

import com.naosim.dddwork.domain.core.FixtureTotalYearMonth

class FixtureAttendanceTotalInquiry {
    static AttendanceTotalInquiry get() {
        new AttendanceTotalInquiry(
                FixtureTotalYearMonth.get()
        )
    }
}
