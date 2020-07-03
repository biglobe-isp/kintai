package com.naosim.dddwork.domain.vo

import java.time.LocalTime

class PunchInTime(
        value: LocalTime
) : Time(value) {
    companion object {
        fun of(hour: Hour, minute: Minute, second: Second = Second(0)): PunchInTime = PunchInTime(LocalTime.of(
                hour.value, minute.value, second.value
        ))
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        if (!super.equals(other)) return false
        return true
    }
}
