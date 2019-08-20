package com.naosim.dddwork.domain.rules;

import com.naosim.dddwork.domain.time.Hour;
import com.naosim.dddwork.domain.time.Minute;
import com.naosim.dddwork.domain.time.RecordedTime;

public class OverTimeRule {

    private final int FinalCutHour = 24;
    private final int FinalCutMinutes = 0;

    private final RecordedTime finalCutTime;

    public OverTimeRule()
    {
        finalCutTime = new RecordedTime( new Hour(FinalCutHour), new Minute(FinalCutMinutes));
    }
    public RecordedTime getFinalCutTime()  { return finalCutTime; }

}
