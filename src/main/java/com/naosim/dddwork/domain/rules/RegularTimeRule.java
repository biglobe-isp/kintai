package com.naosim.dddwork.domain.rules;

import com.naosim.dddwork.domain.time.Hour;
import com.naosim.dddwork.domain.time.Minute;
import com.naosim.dddwork.domain.time.EntryTime;

public enum RegularTimeRule {
    
    REGULAR_TIME_START(9, 0),
    REGULAR_WORKING_DURATION(8,0);

    /*
    @Getter
    private final EntryTime startTime;
    @Getter
    private final EntryTime endTime;

    public static final int REGULAR_WORKING_HOURS = 8;
    public static final int REGULAR_WORKING_MINUTES = 8*60;

    public RegularTimeRule()
    {
        // create regular working hours rule
        EntryTime regularTimeStart = new EntryTime(new Hour(9), new Minute(0));
        EntryTime regularTimeEnd = new EntryTime(new Hour(18), new Minute(0));
        startTime = regularTimeStart;
        endTime = regularTimeEnd;
    }
    */

    private EntryTime entryTime;
    RegularTimeRule(int hour, int minute) {
        entryTime = new EntryTime(new Hour(hour),new Minute(minute));
    }
    public EntryTime getEntryTime()
    {
        return entryTime;
    }
}
