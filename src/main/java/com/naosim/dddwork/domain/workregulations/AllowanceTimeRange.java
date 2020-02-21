package com.naosim.dddwork.domain.workregulations;

import com.naosim.dddwork.domain.TimeUnit;
import lombok.Value;

import java.time.LocalTime;

@Value(staticConstructor="of")
public class AllowanceTimeRange {
    LocalTime minTime;
    LocalTime maxTime;

    public boolean isOutOfRange(TimeUnit value) {
        LocalTime timeValue = LocalTime.of(value.getHour(), value.getMinutes());
        return (minTime.compareTo(timeValue) >= 0 || maxTime.compareTo(timeValue) <= 0);
    }
}
