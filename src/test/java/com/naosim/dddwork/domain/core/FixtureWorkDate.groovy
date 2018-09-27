package com.naosim.dddwork.domain.core

import com.naosim.dddwork.domain.attendance.WorkDate

import java.time.LocalDate

class FixtureWorkDate {
    static WorkDate get() {
        new WorkDate(LocalDate.of(2018, 9, 1))
    }
}
