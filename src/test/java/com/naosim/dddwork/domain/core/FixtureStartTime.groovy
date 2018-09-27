package com.naosim.dddwork.domain.core

import com.naosim.dddwork.domain.attendance.StartTime

import java.time.LocalTime

class FixtureStartTime {
    static StartTime get() {
        new StartTime(LocalTime.of(9, 0, 0))
    }
}
