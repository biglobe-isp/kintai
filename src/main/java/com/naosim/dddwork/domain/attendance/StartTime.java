package com.naosim.dddwork.domain.attendance;

import com.naosim.dddwork.domain.TimeUnit;
import com.naosim.dddwork.domain.workregulations.WorkRegulations;
import lombok.Value;

import java.time.LocalTime;

@Value(staticConstructor="of")
public class StartTime {
    TimeUnit startTime;

    public boolean isLateness(WorkRegulations workRegulations) {
        LocalTime maxTime = workRegulations.getStartTimeRange().getRange().getMaxTime();
        LocalTime compareTime = LocalTime.of(startTime.getHour(), startTime.getMinutes());

        return maxTime.compareTo(compareTime) < 0;
    }
}
