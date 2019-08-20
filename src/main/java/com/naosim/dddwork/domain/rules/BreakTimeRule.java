package com.naosim.dddwork.domain.rules;

import com.naosim.dddwork.domain.time.RecordedTime;
import lombok.Getter;
public class BreakTimeRule {
    @Getter
    private final RecordedTime startTime;
    @Getter
    private final RecordedTime endTime;

    public BreakTimeRule(RecordedTime startTime, RecordedTime endTime)
    {
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public RecordedTime getStartTime() { return startTime; }
    public RecordedTime getEndTime() { return endTime; }

}
