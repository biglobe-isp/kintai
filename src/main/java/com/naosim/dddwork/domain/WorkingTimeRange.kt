package com.naosim.dddwork.domain

import com.naosim.dddwork.domain.vo.*
import java.time.Duration

class WorkingTimeRange(
        punchInTime: PunchInTime,
        punchOutTime: PunchOutTime,
        val scheduledWorkingTimeSpan: ScheduledWorkingTimeSpan,
        val extraWorkingTimeSpan: ExtraWorkingTimeSpan,
        val restTimeSpan: RestTimeSpan
): TimeRange(punchInTime, punchOutTime) {
    companion object {
        fun of(punchInTime: PunchInTime, punchOutTime: PunchOutTime, rule: WorkingTimeRule): WorkingTimeRange {
            var workingTimeSpan = TimeSpan((punchOutTime - punchInTime).value)
            var restTimeSpan = ZERO

            if (punchOutTime == rule.lunchBreakBorder || punchOutTime == rule.nightBreakBorder || punchOutTime == rule.midnightBreakBorder) {
                workingTimeSpan -= of(punchOutTime.minute)
                restTimeSpan += of(punchOutTime.minute)
            }

            if (punchOutTime >= rule.lunchBreakBorder + Hour(1)) {
                workingTimeSpan -= of(Hour(1))
                restTimeSpan += of(Hour(1))
            }

            if (punchOutTime >= rule.nightBreakBorder + Hour(1)) {
                workingTimeSpan -= of(Hour(1))
                restTimeSpan += of(Hour(1))
            }

            if (punchOutTime >= rule.midnightBreakBorder + Hour(1)) {
                workingTimeSpan -= of(Hour(1))
                restTimeSpan += of(Hour(1))
            }

            val scheduledWorkingTimeSpan = rule.scheduledWorkingTimeSpan
            val extraWorkingTimeSpan: TimeSpan = workingTimeSpan - scheduledWorkingTimeSpan

            return WorkingTimeRange(
                    punchInTime = punchInTime,
                    punchOutTime = punchOutTime,
                    scheduledWorkingTimeSpan = if (workingTimeSpan > scheduledWorkingTimeSpan) {
                        rule.scheduledWorkingTimeSpan
                    } else {
                        ScheduledWorkingTimeSpan(workingTimeSpan.value)
                    },
                    extraWorkingTimeSpan = if (extraWorkingTimeSpan > ZERO) {
                        ExtraWorkingTimeSpan(extraWorkingTimeSpan.value)
                    } else {
                        ExtraWorkingTimeSpan.ZERO
                    },
                    restTimeSpan = RestTimeSpan(restTimeSpan.value)
            )
        }
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
        companion object {
            val ZERO: ExtraWorkingTimeSpan = ExtraWorkingTimeSpan(Duration.ZERO)
        }

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
