package com.naosim.dddwork.domain.vo

import java.time.Duration
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

data class Year(
        val value: Int
) {
    init {
        if (value < 1) {
            throw IllegalArgumentException("value is less than 1")
        }
    }
}

data class Month(
        val value: Int
) {
    init {
        if (value < 1) {
            throw IllegalArgumentException("value is less than 1")
        }
        if (value > 12) {
            throw IllegalArgumentException("value is greater than 12")
        }
    }
}

data class Day(
        val value: Int
) {
    init {
        if (value < 1) {
            throw IllegalArgumentException("value is less than 1")
        }
        if (value > 31) {
            throw IllegalArgumentException("value is greater than 31")
        }
    }
}

data class Hour(
        val value: Int
) {
    init {
        if (value < 0) {
            throw IllegalArgumentException("value is less than 0")
        }
        if (value > 23) {
            throw IllegalArgumentException("value is greater than 23")
        }
    }
}

data class Minute(
        val value: Int
) {
    init {
        if (value < 0) {
            throw IllegalArgumentException("value is less than 0")
        }
        if (value > 59) {
            throw IllegalArgumentException("value is greater than 59")
        }
    }
}

data class Second(
        val value: Int
) {
    init {
        if (value < 0) {
            throw IllegalArgumentException("value is less than 0")
        }
        if (value > 59) {
            throw IllegalArgumentException("value is greater than 59")
        }
    }
}

open class Time(
        val value: LocalTime
) : Comparable<Time> {
    val hour: Hour = Hour(value.hour)
    val minute: Minute = Minute(value.minute)
    val second: Second = Second(value.second)

    companion object {
        fun of(hour: Hour, minute: Minute, second: Second = Second(0)): Time = Time(LocalTime.of(
                hour.value, minute.value, second.value
        ))
    }

    override fun compareTo(other: Time): Int = value.compareTo(other.value)

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Time

        if (value != other.value) return false
        if (hour != other.hour) return false
        if (minute != other.minute) return false
        if (second != other.second) return false

        return true
    }

    override fun hashCode(): Int {
        var result = value.hashCode()
        result = 31 * result + hour.hashCode()
        result = 31 * result + minute.hashCode()
        result = 31 * result + second.hashCode()
        return result
    }
}

open class TimeSpan(
        val start: Time,
        val end: Time
) {
    init {
        if (end < start) {
            throw IllegalArgumentException("end is less than start")
        }
    }

    val duration: Duration get() = Duration.between(
            LocalTime.of(start.value.hour, start.value.minute),
            LocalTime.of(end.value.hour, end.value.minute)
    )

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as TimeSpan

        if (start != other.start) return false
        if (end != other.end) return false

        return true
    }

    override fun hashCode(): Int {
        var result = start.hashCode()
        result = 31 * result + end.hashCode()
        return result
    }
}

open class Date(
        val value: LocalDate
) {
    val year: Year get() = Year(value.year)
    val month: Month get() = Month(value.month.value)
    val day: Day get() = Day(value.dayOfMonth)

    companion object {
        fun of(year: Year, month: Month, day: Day): Date = Date(LocalDate.of(
                year.value, month.value, day.value
        ))
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Date

        if (value != other.value) return false

        return true
    }

    override fun hashCode(): Int {
        return value.hashCode()
    }
}

open class DateTime(
        val value: LocalDateTime
) {
    val date: Date = Date(LocalDate.of(value.year, value.month.value, value.dayOfMonth))
    val time: Time = Time(LocalTime.of(value.hour, value.minute, value.second))

    companion object {
        fun of(year: Year, month: Month, day: Day,
               hour: Hour, minute: Minute
        ): DateTime = DateTime(LocalDateTime.of(
                year.value, month.value, day.value,
                hour.value, minute.value
        ))

        fun of(date: Date, time: Time): DateTime = DateTime(LocalDateTime.of(
                date.value, time.value
        ))
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as DateTime

        if (value != other.value) return false

        return true
    }

    override fun hashCode(): Int {
        return value.hashCode()
    }
}