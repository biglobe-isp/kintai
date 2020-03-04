package com.naosim.dddwork.domain.attendance;

import com.naosim.dddwork.domain.TimePoint;
import com.naosim.dddwork.domain.workregulations.WorkRegulations;
import lombok.Value;

@Value(staticConstructor="of")
public class VerifiedAttendanceTime {
    StartTime startTime;
    EndTime endTime;

    public boolean isLateness(WorkRegulations workRegulations) {
        TimePoint maxTimePoint = workRegulations.getStartTimeRange().getRange().getTimeTo();
        TimePoint start = this.startTime.getTimePoint();

        return start.getIntValue() > maxTimePoint.getIntValue();
    }
}
