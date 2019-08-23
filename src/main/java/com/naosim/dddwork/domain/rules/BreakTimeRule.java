package com.naosim.dddwork.domain.rules;

import com.naosim.dddwork.domain.time.EntryTime;
import lombok.Getter;
public class BreakTimeRule {
    @Getter
    private final EntryTime startTime;
    @Getter
    private final EntryTime endTime;

    public BreakTimeRule(EntryTime startTime, EntryTime endTime)
    {
        this.startTime = startTime;
        this.endTime = endTime;
    }
}
