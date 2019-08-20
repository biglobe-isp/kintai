package com.naosim.dddwork.domain.rules;

import com.naosim.dddwork.domain.time.Hour;
import com.naosim.dddwork.domain.time.Minute;
import com.naosim.dddwork.domain.time.RecordedTime;
import lombok.Getter;

public class RegularTimeRule {

    @Getter
    private final RecordedTime startTime;
    @Getter
    private final RecordedTime endTime;

    public RegularTimeRule()
    {
        // create regular working hour rule
        RecordedTime regularTimeStart = new RecordedTime(new Hour(9), new Minute(0));
        RecordedTime regularTimeEnd = new RecordedTime(new Hour(18), new Minute(0));
        startTime = regularTimeStart;
        endTime = regularTimeEnd;
    }

    public RecordedTime getStartTime() { return startTime; }
    public RecordedTime getEndTime() { return endTime; }


}
