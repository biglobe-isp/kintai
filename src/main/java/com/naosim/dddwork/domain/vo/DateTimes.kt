package com.naosim.dddwork.domain.vo

import java.time.Duration
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

data class Year(
        val value: Int
) : Comparable<Year> {
    init {
        if (value < 1) {
            throw IllegalArgumentException("value is less than 1")
        }
    }

    override fun compareTo(other: Year): Int = value.compareTo(other.value)
}

data class Month(
        val value: Int
) : Comparable<Month> {
    init {
        if (value < 1) {
            throw IllegalArgumentException("value is less than 1")
        }
        if (value > 12) {
            throw IllegalArgumentException("value is greater than 12")
        }
    }

    override fun compareTo(other: Month): Int = value.compareTo(other.value)
}

data class Day(
        val value: Int
) : Comparable<Day> {
    init {
        if (value < 1) {
            throw IllegalArgumentException("value is less than 1")
        }
        if (value > 31) {
            throw IllegalArgumentException("value is greater than 31")
        }
    }

    override fun compareTo(other: Day): Int = value.compareTo(other.value)
}

data class Hour(
        val value: Int
) : Comparable<Hour> {
    init {
        if (value < 0) {
            throw IllegalArgumentException("Hour value is less than 0 ($value)")
        }
    }

    operator fun plus(other: Hour): Hour = Hour(value + other.value)

    override fun compareTo(other: Hour): Int = value.compareTo(other.value)
}

data class Minute(
        val value: Int
) : Comparable<Minute> {
    init {
        if (value < 0) {
            throw IllegalArgumentException("Minute value is less than 0 ($value)")
        }
    }

    override fun compareTo(other: Minute): Int = value.compareTo(other.value)

    operator fun minus(other: Minute): Minute = Minute(value - other.value)
    operator fun minus(other: Hour): Minute = Minute(value - other.value * 60)

    operator fun plus(other: Minute): Minute = Minute(value + other.value)
    operator fun plus(other: Hour): Minute = Minute(value + other.value * 60)
}

data class Second(
        val value: Int
) : Comparable<Second> {
    init {
        if (value < 0) {
            throw IllegalArgumentException("Second value is less than 0 ($value)")
        }
    }

    override fun compareTo(other: Second): Int = value.compareTo(other.value)
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

    operator fun minus(other: Time): TimeSpan = TimeSpan(Duration.between(other.value, value))
    operator fun plus(other: Second): Time = Time(LocalTime.of(hour.value, minute.value, second.value + other.value))
    operator fun plus(other: Minute): Time = Time(LocalTime.of(hour.value, minute.value + other.value, second.value))
    operator fun plus(other: Hour): Time = Time(LocalTime.of(hour.value + other.value, minute.value, second.value))
    operator fun plus(other: TimeSpan): Time = Time(LocalTime.of(hour.value, minute.value, second.value) + other.value)

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

    override fun toString(): String {
        return "Time(value=$value, hour=$hour, minute=$minute, second=$second)"
    }
}

open class TimeSpan(
        val value: Duration
) : Comparable<TimeSpan> {
    companion object {
        val ZERO: TimeSpan = TimeSpan(Duration.ZERO)

        private fun ofMinutes(minuteSpan: Long): TimeSpan = TimeSpan(Duration.ofMinutes(minuteSpan))
        private fun ofMinutes(minuteSpan: Int): TimeSpan = ofMinutes(minuteSpan.toLong())
        fun of(minuteSpan: Minute): TimeSpan = ofMinutes(minuteSpan.value)

        private fun ofHours(hourSpan: Long) = TimeSpan(Duration.ofHours(hourSpan))
        private fun ofHours(hourSpan: Int) = ofHours(hourSpan.toLong())
        fun of(hourSpan: Hour): TimeSpan = ofHours(hourSpan.value)
    }

    val minutesPart: Minute get() = Minute(value.toMinutes().toInt() % 60)
    val hoursPart: Hour get() = Hour(value.toMinutes().toInt() / 60)

    open operator fun minus(other: TimeSpan): TimeSpan = TimeSpan(value - other.value)
    open operator fun plus(other: TimeSpan): TimeSpan = TimeSpan(value + other.value)
    override fun compareTo(other: TimeSpan): Int = value.compareTo(other.value)

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as TimeSpan

        if (value != other.value) return false

        return true
    }

    override fun hashCode(): Int {
        return value.hashCode()
    }

    override fun toString(): String {
        return "TimeSpan(value=$value)"
    }
}

open class TimeRange(
        val start: Time,
        val end: Time
) {
    init {
        if (end < start) {
            throw IllegalArgumentException("end is less than start (end: $end, start: $start)")
        }
    }

    fun toTimeSpan(): TimeSpan = TimeSpan(Duration.between(start.value, end.value))

    operator fun contains(time: Time) = time >= start && time <= end

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        if (!super.equals(other)) return false

        other as TimeRange

        if (start != other.start) return false
        if (end != other.end) return false

        return true
    }

    override fun hashCode(): Int {
        var result = super.hashCode()
        result = 31 * result + start.hashCode()
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
) : Comparable<DateTime> {
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

    override fun compareTo(other: DateTime): Int = value.compareTo(other.value)

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