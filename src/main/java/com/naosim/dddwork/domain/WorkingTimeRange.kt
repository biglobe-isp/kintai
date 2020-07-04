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
        var workingTimeSpanAsMinuteValue: Long = (punchOutTime - punchInTime).value.toMinutes()
        var restTimeSpanAsMinuteValue: Long = 0L

        if (punchOutTime == rule.lunchBreakBorder) {
            workingTimeSpanAsMinuteValue -= punchOutTime.minute.value
            restTimeSpanAsMinuteValue += punchOutTime.minute.value
        }
        if (punchOutTime >= rule.lunchBreakBorder + Hour(1)) {
            workingTimeSpanAsMinuteValue -= 60L
            restTimeSpanAsMinuteValue += 60L
        }

        if (punchOutTime == rule.nightBreakBorder) {
            workingTimeSpanAsMinuteValue -= punchOutTime.minute.value
            restTimeSpanAsMinuteValue += punchOutTime.minute.value
        }
        if (punchOutTime >= rule.nightBreakBorder + Hour(1)) {
            workingTimeSpanAsMinuteValue -= 60L
            restTimeSpanAsMinuteValue += 60L
        }

        if (punchOutTime == rule.midnightBreakBorder) {
            workingTimeSpanAsMinuteValue -= punchOutTime.minute.value
            restTimeSpanAsMinuteValue += punchOutTime.minute.value
        }
        if (punchOutTime >= rule.midnightBreakBorder + Hour(1)) {
            workingTimeSpanAsMinuteValue -= 60L
            restTimeSpanAsMinuteValue += 60L
        }

        val extraWorkingTimeSpanAsMinuteValue: Long =
                rule.scheduledWorkingTimeSpan.value.toMinutes() - workingTimeSpanAsMinuteValue

        scheduledWorkingTimeSpan =  if (workingTimeSpanAsMinuteValue > 8 * 60) {
            rule.scheduledWorkingTimeSpan
        } else {
            ScheduledWorkingTimeSpan(Duration.ofMinutes(workingTimeSpanAsMinuteValue))
        }

        extraWorkingTimeSpan = if (workingTimeSpanAsMinuteValue > 8 * 60) {
            ExtraWorkingTimeSpan(Duration.ofMinutes(extraWorkingTimeSpanAsMinuteValue - 8 * 60))
        } else {
            ExtraWorkingTimeSpan(Duration.ZERO)
        }

        restTimeSpan = RestTimeSpan(Duration.ofMillis(restTimeSpanAsMinuteValue))
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
