package com.naosim.dddwork.domain.rules;

import com.naosim.dddwork.domain.time.RecordedTime;

public class OverTimeRule {

    private final RecordedTime finalCutTime;

    public OverTimeRule(RecordedTime finalCutTime)
    {
        this.finalCutTime = finalCutTime;
    }

    public RecordedTime getFinalCutTime()  { return finalCutTime; }

}
