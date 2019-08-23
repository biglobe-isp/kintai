package com.naosim.dddwork.domain.rules;

import com.naosim.dddwork.domain.time.Hour;
import com.naosim.dddwork.domain.time.Minute;
import com.naosim.dddwork.domain.time.EntryTime;
import lombok.Getter;

public class RegularTimeRule {

    @Getter
    private final EntryTime startTime;
    @Getter
    private final EntryTime endTime;

    public static final int REGULAR_WORKING_HOURS = 8;
    public static final int REGULAR_WORKING_MINUTES = 8*60;

    public RegularTimeRule()
    {
        // create regular working hour rule
        EntryTime regularTimeStart = new EntryTime(new Hour(9), new Minute(0));
        EntryTime regularTimeEnd = new EntryTime(new Hour(18), new Minute(0));
        startTime = regularTimeStart;
        endTime = regularTimeEnd;
    }

    //public EntryTime getStartTime() { return startTime; }
    //public EntryTime getEndTime() { return endTime; }


}
