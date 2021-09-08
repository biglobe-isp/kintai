package com.naosim.dddwork.kintai.domain.timerecord.regulation;

import com.naosim.dddwork.kintai.domain.timerecord.TimeInterval;
import lombok.Value;

@Value
public class RegulatedWorkingTimeInterval {
    TimeInterval interval;

    public boolean hasFollowedRegulatedStartTime(TimeInterval comparison) {
        if (comparison.getStartHour() > this.interval.getStartHour()) {
            return false;
        }
        if (comparison.getStartHour() == this.interval.getStartHour()
                && comparison.getStartMinute() > this.interval.getStartMinute()) {
            return false;
        }
        return true;
    }
}
