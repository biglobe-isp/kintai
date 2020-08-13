package com.naosim.dddwork.domain

import com.naosim.dddwork.domain.vo.*
import java.time.Duration

data class WorkingTimeRule(
        val scheduledWorkingTimeSpan: WorkingTimeRange.ScheduledWorkingTimeSpan =
                WorkingTimeRange.ScheduledWorkingTimeSpan(Duration.ofHours(8L)),
        val breakTimeRanges: List<TimeRange> = listOf(
                TimeRange(Time.of(Hour(12), Minute(0)), Time.of(Hour(13), Minute(0))),
                TimeRange(Time.of(Hour(18), Minute(0)), Time.of(Hour(19), Minute(0))),
                TimeRange(Time.of(Hour(21), Minute(0)), Time.of(Hour(22), Minute(0)))
        )
) {
    fun calcWorkingTimeRange(punchInTime: PunchInTime, punchOutTime: PunchOutTime): WorkingTimeRange {
        val restTimeSpan = calcRestTimeSpan(punchInTime, punchOutTime)
        val workingTimeSpan = punchOutTime - punchInTime - restTimeSpan
        val scheduledWorkingTimeSpan =
                if (workingTimeSpan < scheduledWorkingTimeSpan)
                    WorkingTimeRange.ScheduledWorkingTimeSpan.of(workingTimeSpan)
                else scheduledWorkingTimeSpan
        val extraWorkingTimeSpan =
                if (workingTimeSpan > scheduledWorkingTimeSpan)
                    WorkingTimeRange.ExtraWorkingTimeSpan.of(workingTimeSpan - scheduledWorkingTimeSpan)
                else WorkingTimeRange.ExtraWorkingTimeSpan.ZERO

        return WorkingTimeRange(
                punchInTime = punchInTime,
                punchOutTime = punchOutTime,
                scheduledWorkingTimeSpan = scheduledWorkingTimeSpan,
                extraWorkingTimeSpan = extraWorkingTimeSpan,
                restTimeSpan = restTimeSpan
        )
    }

    private fun calcRestTimeSpan(punchInTime: PunchInTime, punchOutTime: PunchOutTime): WorkingTimeRange.RestTimeSpan {
        var start: Time = punchInTime
        var end: Time = punchOutTime
        var restTimeSpan = TimeSpan.ZERO

        breakTimeRanges.forEach {
            if (punchOutTime < it.start) {
                return@forEach
            }

            val breakTimeRange = TimeRange(
                    start = if (punchInTime > it.start) punchInTime else it.start,
                    end = if (punchOutTime < it.end) punchOutTime else it.end
            )

            when {
                start in breakTimeRange && end in breakTimeRange -> {
                    restTimeSpan += breakTimeRange.toTimeSpan()
                    return@forEach
                }
                start in breakTimeRange -> {
                    restTimeSpan += breakTimeRange.toTimeSpan()
                    start = breakTimeRange.end
                }
                end in breakTimeRange -> {
                    restTimeSpan += breakTimeRange.toTimeSpan()
                    end = breakTimeRange.start
                }
                else -> {
                    restTimeSpan += breakTimeRange.toTimeSpan()
                }
            }
        }

        return WorkingTimeRange.RestTimeSpan.of(restTimeSpan)
    }
}
