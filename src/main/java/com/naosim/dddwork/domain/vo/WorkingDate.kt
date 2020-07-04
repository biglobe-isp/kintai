package com.naosim.dddwork.domain.vo

import java.time.LocalDate

class WorkingDate(
        value: LocalDate
) : Date(value) {
    companion object {
        fun of(year: Year, month: Month, day: Day): WorkingDate = WorkingDate(LocalDate.of(
                year.value, month.value, day.value
        ))

        fun of(date: Date): WorkingDate = WorkingDate(date.value)
    }
}
