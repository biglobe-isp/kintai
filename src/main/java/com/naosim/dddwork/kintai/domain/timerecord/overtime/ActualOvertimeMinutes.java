package com.naosim.dddwork.kintai.domain.timerecord.overtime;

import com.naosim.dddwork.kintai.domain.timerecord.regulation.RegulatedWorkingTimeMinutes;
import com.naosim.dddwork.kintai.domain.timerecord.workingtime.ActualWorkingTimeMinutes;
import lombok.Value;

@Value
public class ActualOvertimeMinutes {

    int minutes;

    public ActualOvertimeMinutes(ActualWorkingTimeMinutes actualWorkingTimeMinutes, RegulatedWorkingTimeMinutes regulatedWorkingTimeMinutes) {
        if (hasOvertime(actualWorkingTimeMinutes, regulatedWorkingTimeMinutes)) {
            this.minutes = actualWorkingTimeMinutes.getMinutes() - regulatedWorkingTimeMinutes.getMinutes();
            return;
        }
        this.minutes = 0;

    }

    private boolean hasOvertime(ActualWorkingTimeMinutes actualWorkingTimeMinutes, RegulatedWorkingTimeMinutes regulatedWorkingTimeMinutes) {
        if (actualWorkingTimeMinutes.getMinutes() - regulatedWorkingTimeMinutes.getMinutes() > 0) {
            return true;
        }
        return false;
    }
}
