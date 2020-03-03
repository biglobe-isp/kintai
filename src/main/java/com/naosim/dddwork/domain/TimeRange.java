package com.naosim.dddwork.domain;

import lombok.Value;

import java.time.LocalTime;

@Value(staticConstructor="of")
public class TimeRange {
    TimePoint timeFrom;
    TimePoint timeTo;

    public int getRangeMinutes() {
        TimeUnit from = TimeUnit.of(this.timeFrom.getHour(), this.timeFrom.getMinutes());
        TimeUnit to = TimeUnit.of(this.timeTo.getHour(), this.timeTo.getMinutes());
        int fromMinutes = from.getTotalMinutes();
        int toMinutes = to.getTotalMinutes();

        if (fromMinutes > toMinutes) { return 0; }

        return toMinutes - fromMinutes;
    }

    public boolean isOutOfRange(LocalTime timeValue) {
        return (this.getLocalTimeFrom().compareTo(timeValue) >= 0 || this.getLocalTimeTo().compareTo(timeValue) <= 0);
    }

    private LocalTime getLocalTimeFrom() {
        return LocalTime.of(this.timeFrom.getHour(), this.timeFrom.getMinutes());
    }

    private LocalTime getLocalTimeTo() {
        return LocalTime.of(this.timeTo.getHour(), this.timeTo.getMinutes());
    }
}
