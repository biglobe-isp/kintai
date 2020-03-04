package com.naosim.dddwork.domain.attendance;

import lombok.Value;

@Value(staticConstructor="of")
public class NotVerifiedAttendanceTime {
    StartTime startTime;
    EndTime endTime;

    public boolean isStartGreaterThanEnd() {
        return this.startTime.getTimePoint().getIntValue() > this.endTime.getTimePoint().getIntValue();
    }
}
