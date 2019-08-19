package com.naosim.dddwork.domain.rules;

import com.naosim.dddwork.domain.time.RecordedTime;
import lombok.Getter;

public class RegularTimeRule {

    @Getter
    private final RecordedTime startTime;
    @Getter
    private final RecordedTime endTime;

    public RegularTimeRule(RecordedTime startTime, RecordedTime endTime)
    {
        this.startTime = startTime;
        this.endTime = endTime;
    }

}
