package com.naosim.dddwork.domain.core

import com.naosim.dddwork.domain.attendance.TotalYearMonth

import java.time.YearMonth

class FixtureTotalYearMonth {
    static TotalYearMonth get() {
        new TotalYearMonth(YearMonth.of(2018, 9))
    }
}
