package com.naosim.dddwork.kintai.domain.timerecord.overtime;

import com.naosim.dddwork.kintai.domain.timerecord.TimeLength;
import com.naosim.dddwork.kintai.domain.timerecord.regulation.RegulatedWorkingTimeMinutes;
import com.naosim.dddwork.kintai.domain.timerecord.workingtime.ActualWorkingTimeMinutes;
import lombok.Value;

import static com.naosim.dddwork.kintai.domain.timerecord.TimeUnits.MINUTES;

@Value
public class ActualOvertimeMinutes {

    TimeLength length;

    public ActualOvertimeMinutes(ActualWorkingTimeMinutes actualWorkingTimeMinutes, RegulatedWorkingTimeMinutes regulatedWorkingTimeMinutes) {
        if (hasOvertime(actualWorkingTimeMinutes, regulatedWorkingTimeMinutes)) {
            this.length = actualWorkingTimeMinutes.getLength().subtract(regulatedWorkingTimeMinutes.getLength());
            return;
        }
        this.length = new TimeLength(0, MINUTES);

    }

    public int intValue() {
        return (int)this.length.getLength();
    }

    private boolean hasOvertime(ActualWorkingTimeMinutes actualWorkingTimeMinutes, RegulatedWorkingTimeMinutes regulatedWorkingTimeMinutes) {
        if (actualWorkingTimeMinutes.getLength().isLonger(regulatedWorkingTimeMinutes.getLength())) {
            return true;
        }
        return false;
    }
}
