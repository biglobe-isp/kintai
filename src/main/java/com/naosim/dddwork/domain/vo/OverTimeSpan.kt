package com.naosim.dddwork.domain.vo

class OverTimeSpan(
        start: Time,
        end: Time
) : TimeSpan(start, end) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        if (!super.equals(other)) return false
        return true
    }
}
