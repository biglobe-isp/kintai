package com.naosim.dddwork.kintai.domain.timerecord.actualtime;

import com.naosim.dddwork.kintai.domain.timerecord.actualtime.overtime.ActualOvertimeMinutes;
import com.naosim.dddwork.kintai.domain.timerecord.actualtime.workingtime.ActualWorkingTimeMinutes;
import lombok.NonNull;
import lombok.Value;

@Value
public class ActualMinutes {
    @NonNull
    ActualWorkingTimeMinutes actualWorkingTimeMinutes;
    @NonNull
    ActualOvertimeMinutes actualOvertimeMinutes;

    //HACK: AttendanceAggregationMonthlyでも同じ関数を作っており、冗長になっている
    public int convertActualWorkingTimeMinutesToHour() {
        return (int)this.actualWorkingTimeMinutes.getLength().minuteToHour().getLength();
    }
    public int convertActualOvertimeMinutesToHour() {
        return (int)this.actualOvertimeMinutes.getLength().minuteToHour().getLength();
    }
    public int extractRemainderActualWorkingTimeMinutes() {
        return (int)this.actualWorkingTimeMinutes.getLength().extractRemainderMinutes().getLength();
    }
    public int extractRemainderActualOvertimeMinutes() {
        return (int)this.actualOvertimeMinutes.getLength().extractRemainderMinutes().getLength();
    }
}
