package com.naosim.dddwork.domain.attendance;

import com.naosim.dddwork.domain.TimePoint;
import com.naosim.dddwork.domain.workregulations.WorkRegulations;
import lombok.Value;

import java.time.LocalTime;

@Value(staticConstructor="of")
public class StartTime {
    TimePoint timePoint;

    public boolean isLateness(WorkRegulations workRegulations) {
        TimePoint maxTimePoint = workRegulations.getStartTimeRange().getRange().getTimeTo();
        LocalTime maxTime = LocalTime.of(maxTimePoint.getHour(), maxTimePoint.getMinutes());
        LocalTime compareTime = LocalTime.of(this.timePoint.getHour(), this.timePoint.getMinutes());

        return maxTime.compareTo(compareTime) < 0;
    }
}
