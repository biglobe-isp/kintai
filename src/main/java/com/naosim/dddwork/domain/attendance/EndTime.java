package com.naosim.dddwork.domain.attendance;

import com.naosim.dddwork.domain.TimeUnit;
import com.naosim.dddwork.domain.workregulations.WorkRegulations;
import lombok.Value;

@Value(staticConstructor="of")
public class EndTime {
    TimeUnit endTime;

    public boolean isLeaveEarly(WorkRegulations workRegulations) {
        if (workRegulations.getEndTimeRange().getRange().isOutOfRange(endTime)) {
            return false;
        }
        return true;
    }
}
