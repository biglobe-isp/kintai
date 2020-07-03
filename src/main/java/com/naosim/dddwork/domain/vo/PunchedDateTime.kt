package com.naosim.dddwork.domain.vo

import java.time.LocalDateTime

class PunchedDateTime(
        value: LocalDateTime
) : DateTime(value) {
    companion object {
        fun of(year: Year, month: Month, day: Day,
               hour: Hour, minute: Minute, second: Second
        ): PunchedDateTime = PunchedDateTime(LocalDateTime.of(
                year.value, month.value, day.value,
                hour.value, minute.value, second.value
        ))

        fun of(date: Date, time: Time): PunchedDateTime = PunchedDateTime(LocalDateTime.of(
                date.value, time.value
        ))

        fun of(dateTime: DateTime): PunchedDateTime = PunchedDateTime(dateTime.value)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        if (!super.equals(other)) return false
        return true
    }
}
