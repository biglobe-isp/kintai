package com.naosim.dddwork.domain.core

import com.naosim.dddwork.domain.attendance.EndTime

import java.time.LocalTime

class FixtureEndTime {
    static EndTime get() {
        new EndTime(LocalTime.of(18, 0, 0))
    }
}
