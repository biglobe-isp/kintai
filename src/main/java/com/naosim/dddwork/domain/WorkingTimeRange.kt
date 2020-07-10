package com.naosim.dddwork.domain

import com.naosim.dddwork.domain.vo.*
import java.time.Duration

class WorkingTimeRange(
        punchInTime: PunchInTime,
        punchOutTime: PunchOutTime,
        rule: WorkingTimeRule
): TimeRange(punchInTime, punchOutTime) {
    val scheduledWorkingTimeSpan: ScheduledWorkingTimeSpan
    val extraWorkingTimeSpan: ExtraWorkingTimeSpan
    val restTimeSpan: RestTimeSpan

    init {
        var workingTimeSpanAsMinute = Minute((punchOutTime - punchInTime).value.toMinutes().toInt())
        var restTimeSpanAsMinute = Minute(0)

        if (punchOutTime == rule.lunchBreakBorder) {
            workingTimeSpanAsMinute -= punchOutTime.minute
            restTimeSpanAsMinute += punchOutTime.minute
        }
        if (punchOutTime >= rule.lunchBreakBorder + Hour(1)) {
            workingTimeSpanAsMinute -= Hour(1)
            restTimeSpanAsMinute += Hour(1)
        }

        if (punchOutTime == rule.nightBreakBorder) {
            workingTimeSpanAsMinute -= punchOutTime.minute
            restTimeSpanAsMinute += punchOutTime.minute
        }
        if (punchOutTime >= rule.nightBreakBorder + Hour(1)) {
            workingTimeSpanAsMinute -= Hour(1)
            restTimeSpanAsMinute += Hour(1)
        }

        if (punchOutTime == rule.midnightBreakBorder) {
            workingTimeSpanAsMinute -= punchOutTime.minute
            restTimeSpanAsMinute += punchOutTime.minute
        }
        if (punchOutTime >= rule.midnightBreakBorder + Hour(1)) {
            workingTimeSpanAsMinute -= Hour(1)
            restTimeSpanAsMinute += Hour(1)
        }

        val scheduledWorkingTimeSpanMinutes = Minute(rule.scheduledWorkingTimeSpan.value.toMinutes().toInt())
        val extraWorkingTimeSpanAsMinute: Minute = workingTimeSpanAsMinute - scheduledWorkingTimeSpanMinutes

        scheduledWorkingTimeSpan = if (workingTimeSpanAsMinute > scheduledWorkingTimeSpanMinutes) {
            rule.scheduledWorkingTimeSpan
        } else {
            ScheduledWorkingTimeSpan(Duration.ofMinutes(workingTimeSpanAsMinute.value.toLong()))
        }

        extraWorkingTimeSpan = if (extraWorkingTimeSpanAsMinute > Minute(0)) {
            ExtraWorkingTimeSpan(Duration.ofMinutes(extraWorkingTimeSpanAsMinute.value.toLong()))
        } else {
            ExtraWorkingTimeSpan(Duration.ZERO)
        }

        restTimeSpan = RestTimeSpan(Duration.ofMillis(restTimeSpanAsMinute.value.toLong()))
    }

    class ScheduledWorkingTimeSpan(
            value: Duration
    ): TimeSpan(value) {
        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (javaClass != other?.javaClass) return false
            if (!super.equals(other)) return false
            return true
        }
    }

    class ExtraWorkingTimeSpan(
            value: Duration
    ): TimeSpan(value) {
        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (javaClass != other?.javaClass) return false
            if (!super.equals(other)) return false
            return true
        }
    }

    class RestTimeSpan(
            value: Duration
    ): TimeSpan(value) {
        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (javaClass != other?.javaClass) return false
            if (!super.equals(other)) return false
            return true
        }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        if (!super.equals(other)) return false

        other as WorkingTimeRange

        if (scheduledWorkingTimeSpan != other.scheduledWorkingTimeSpan) return false
        if (extraWorkingTimeSpan != other.extraWorkingTimeSpan) return false
        if (restTimeSpan != other.restTimeSpan) return false

        return true
    }

    override fun hashCode(): Int {
        var result = super.hashCode()
        result = 31 * result + scheduledWorkingTimeSpan.hashCode()
        result = 31 * result + extraWorkingTimeSpan.hashCode()
        result = 31 * result + restTimeSpan.hashCode()
        return result
    }
}
