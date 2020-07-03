package com.naosim.dddwork.domain.vo

import java.time.LocalDate

class WorkDate(
        value: LocalDate
) : Date(value) {
    companion object {
        fun of(year: Year, month: Month, day: Day): WorkDate = WorkDate(LocalDate.of(
                year.value, month.value, day.value
        ))

        fun of(date: Date): WorkDate = WorkDate(date.value)
    }
}
