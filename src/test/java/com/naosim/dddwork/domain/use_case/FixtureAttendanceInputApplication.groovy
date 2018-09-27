package com.naosim.dddwork.domain.use_case

import com.naosim.dddwork.domain.core.FixtureEndTime
import com.naosim.dddwork.domain.core.FixtureStartTime
import com.naosim.dddwork.domain.core.FixtureWorkDate

class FixtureAttendanceInputApplication {
    static AttendanceInputApplication get() {
        new AttendanceInputApplication(
                FixtureWorkDate.get(),
                FixtureStartTime.get(),
                FixtureEndTime.get()
        )
    }
}
