package com.naosim.dddwork.domain.rules;

import com.naosim.dddwork.domain.time.EntryTime;
import com.naosim.dddwork.domain.time.Hour;
import com.naosim.dddwork.domain.time.Minute;

public enum RegularTimeRule {
    REGULAR_TIME_START(9, 0),
    REGULAR_WORKING_DURATION(8, 0);
    private EntryTime entryTime;

    RegularTimeRule(int hour, int minute) {
        entryTime = new EntryTime(new Hour(hour), new Minute(minute));
    }

    public EntryTime getEntryTime() {
        return entryTime;
    }
}
