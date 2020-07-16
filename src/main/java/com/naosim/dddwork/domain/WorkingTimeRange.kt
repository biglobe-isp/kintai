package com.naosim.dddwork.domain

import com.naosim.dddwork.domain.vo.PunchInTime
import com.naosim.dddwork.domain.vo.PunchOutTime
import com.naosim.dddwork.domain.vo.TimeRange
import com.naosim.dddwork.domain.vo.TimeSpan
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
            var workingTimeSpan: TimeSpan = punchOutTime - punchInTime
            var restTimeSpan = RestTimeSpan.ZERO

            if (rule.evalBreakTimeOnPunchedOut(punchOutTime)) {
                workingTimeSpan -= TimeSpan.of(punchOutTime.minute)
                restTimeSpan += TimeSpan.of(punchOutTime.minute)
            }

            if (rule.evalPastLunchBreakOnPunchedOut(punchOutTime)) {
                workingTimeSpan -= WorkingTimeRule.FIXED_REST_TIME
                restTimeSpan += WorkingTimeRule.FIXED_REST_TIME
            }

            if (rule.evalPastNightBreakOnPunchedOut(punchOutTime)) {
                workingTimeSpan -= WorkingTimeRule.FIXED_REST_TIME
                restTimeSpan += WorkingTimeRule.FIXED_REST_TIME
            }

            if (rule.evalPastMidnightBreakOnPunchedOut(punchOutTime)) {
                workingTimeSpan -= WorkingTimeRule.FIXED_REST_TIME
                restTimeSpan += WorkingTimeRule.FIXED_REST_TIME
            }

            return WorkingTimeRange(
                    punchInTime = punchInTime,
                    punchOutTime = punchOutTime,
                    scheduledWorkingTimeSpan = rule.bindToScheduledWorkingTimeSpan(workingTimeSpan),
                    extraWorkingTimeSpan = ExtraWorkingTimeSpan.of(workingTimeSpan - rule.scheduledWorkingTimeSpan),
                    restTimeSpan = RestTimeSpan.of(restTimeSpan)
            )
        }
    }

    val actualWorkingTimeSpan: ActualWorkingTimeSpan get() =
        ActualWorkingTimeSpan.of(scheduledWorkingTimeSpan) + extraWorkingTimeSpan

    class ScheduledWorkingTimeSpan(
            value: Duration
    ): TimeSpan(value) {
        companion object {
            val ZERO: ScheduledWorkingTimeSpan = ScheduledWorkingTimeSpan(Duration.ZERO)
            fun of(value: Duration): ScheduledWorkingTimeSpan = if (value.isNegative) ZERO else ScheduledWorkingTimeSpan(value)
            fun of(timeSpan: TimeSpan): ScheduledWorkingTimeSpan = if (timeSpan.value.isNegative) ZERO else ScheduledWorkingTimeSpan(timeSpan.value)
        }

        override operator fun minus(other: TimeSpan): ScheduledWorkingTimeSpan = ScheduledWorkingTimeSpan(value - other.value)
        override operator fun plus(other: TimeSpan): ScheduledWorkingTimeSpan = ScheduledWorkingTimeSpan(value + other.value)

        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (javaClass != other?.javaClass) return false
            if (!super.equals(other)) return false
            return true
        }
    }

    class ActualWorkingTimeSpan(
            value: Duration
    ): TimeSpan(value) {
        companion object {
            val ZERO: ActualWorkingTimeSpan = ActualWorkingTimeSpan(Duration.ZERO)
            fun of(value: Duration): ActualWorkingTimeSpan = if (value.isNegative) ZERO else ActualWorkingTimeSpan(value)
            fun of(timeSpan: TimeSpan): ActualWorkingTimeSpan = if (timeSpan.value.isNegative) ZERO else ActualWorkingTimeSpan(timeSpan.value)
        }

        override operator fun minus(other: TimeSpan): ActualWorkingTimeSpan = ActualWorkingTimeSpan(value - other.value)
        override operator fun plus(other: TimeSpan): ActualWorkingTimeSpan = ActualWorkingTimeSpan(value + other.value)

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
            fun of(value: Duration): ExtraWorkingTimeSpan = if (value.isNegative) ZERO else ExtraWorkingTimeSpan(value)
            fun of(timeSpan: TimeSpan): ExtraWorkingTimeSpan = if (timeSpan.value.isNegative) ZERO else ExtraWorkingTimeSpan(timeSpan.value)
        }

        override operator fun minus(other: TimeSpan): ExtraWorkingTimeSpan = ExtraWorkingTimeSpan(value - other.value)
        override operator fun plus(other: TimeSpan): ExtraWorkingTimeSpan = ExtraWorkingTimeSpan(value + other.value)

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
        companion object {
            val ZERO: RestTimeSpan = RestTimeSpan(Duration.ZERO)
            fun of(value: Duration): RestTimeSpan = if (value.isNegative) ZERO else RestTimeSpan(value)
            fun of(timeSpan: TimeSpan): RestTimeSpan = if (timeSpan.value.isNegative) ZERO else RestTimeSpan(timeSpan.value)
        }

        override operator fun minus(other: TimeSpan): RestTimeSpan = RestTimeSpan(value - other.value)
        override operator fun plus(other: TimeSpan): RestTimeSpan = RestTimeSpan(value + other.value)

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
