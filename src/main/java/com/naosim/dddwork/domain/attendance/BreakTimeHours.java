package com.naosim.dddwork.domain.attendance;

import com.naosim.dddwork.domain.TimeUnit;
import com.naosim.dddwork.domain.workregulations.WorkRegulations;
import lombok.Value;

@Value
public class BreakTimeHours {
    TimeUnit breakTimeHours;

    public BreakTimeHours(AttendanceTime attendanceTime, WorkRegulations workRegulations){
        // TODO:未実装
        breakTimeHours = null;
    }
}
