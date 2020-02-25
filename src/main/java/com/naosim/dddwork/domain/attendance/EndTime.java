package com.naosim.dddwork.domain.attendance;

import com.naosim.dddwork.domain.TimePoint;
import com.naosim.dddwork.domain.workregulations.WorkRegulations;
import lombok.Value;

import java.time.LocalTime;

@Value(staticConstructor="of")
public class EndTime {
    TimePoint timePoint;

    public boolean isLeaveEarly(WorkRegulations workRegulations) {
        if (workRegulations.getEndTimeRange().getRange().isOutOfRange(this.timePoint.getLocalTime())) {
            return false;
        }
        return true;
    }

    public boolean isExceedLimitTime() {
        if (this.timePoint.getHour() > LocalTime.MAX.getHour()) {
            return true;
        }
        return false;
    }

    public TimePoint getDeemedEndTime() {
        if (isExceedLimitTime()) {
            return TimePoint.of(LocalTime.MAX.getHour() + 1, 0);
        } else {
            return this.timePoint;
        }
    }
}
