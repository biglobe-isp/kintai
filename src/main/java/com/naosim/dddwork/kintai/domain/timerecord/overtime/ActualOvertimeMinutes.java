package com.naosim.dddwork.kintai.domain.timerecord.overtime;

import com.naosim.dddwork.kintai.domain.timerecord.TimeLength;
import com.naosim.dddwork.kintai.domain.timerecord.regulation.RegulatedWorkingTimeMinutes;
import com.naosim.dddwork.kintai.domain.timerecord.workingtime.ActualWorkingTimeMinutes;
import lombok.Value;

import static com.naosim.dddwork.kintai.domain.timerecord.TimeUnits.MINUTES;

@Value
public class ActualOvertimeMinutes {

    TimeLength minutes;

    public ActualOvertimeMinutes(ActualWorkingTimeMinutes actualWorkingTimeMinutes, RegulatedWorkingTimeMinutes regulatedWorkingTimeMinutes) {
        if (hasOvertime(actualWorkingTimeMinutes, regulatedWorkingTimeMinutes)) {
            this.minutes = actualWorkingTimeMinutes.getMinutes().subtract(regulatedWorkingTimeMinutes.getMinutes());
            return;
        }
        this.minutes = new TimeLength(0, MINUTES);

    }

    public int intValue() {
        return (int)this.minutes.getLength();
    }

    private boolean hasOvertime(ActualWorkingTimeMinutes actualWorkingTimeMinutes, RegulatedWorkingTimeMinutes regulatedWorkingTimeMinutes) {
        if (actualWorkingTimeMinutes.getMinutes().moreThan(regulatedWorkingTimeMinutes.getMinutes())) {
            return true;
        }
        return false;
    }
}
