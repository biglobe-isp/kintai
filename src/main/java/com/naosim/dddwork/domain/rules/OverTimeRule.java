package com.naosim.dddwork.domain.rules;

import com.naosim.dddwork.domain.time.RecordedTime;
import lombok.Getter;
public class OverTimeRule {

    @Getter
    private final RecordedTime finalCutTime;

    public OverTimeRule(RecordedTime finalCutTime)
    {
        this.finalCutTime = finalCutTime;
    }


}
