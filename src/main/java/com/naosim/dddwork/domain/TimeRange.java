package com.naosim.dddwork.domain;

import lombok.Value;

@Value(staticConstructor="of")
public class TimeRange {
    TimeUnit timeFrom;
    TimeUnit timeTo;

    private int convertMinutes(TimeUnit timeUnit) {
        return timeUnit.getHour() * 60 + timeUnit.getMinutes();
    }

    public int getRangeMinutes() {
        int fromMinutes = convertMinutes(timeFrom);
        int toMinutes = convertMinutes(timeTo);
        if (fromMinutes > toMinutes) {
            return 0;
        }
        return toMinutes - fromMinutes;
    }
}
