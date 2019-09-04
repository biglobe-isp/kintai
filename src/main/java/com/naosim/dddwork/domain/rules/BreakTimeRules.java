package com.naosim.dddwork.domain.rules;

import com.naosim.dddwork.domain.time.EntryTime;
import com.naosim.dddwork.domain.time.Hour;
import com.naosim.dddwork.domain.time.Minute;

public enum BreakTimeRules {
    LUNCH_BREAK_TIME(12, 0, 13, 0),
    EVENING_BREAK(18, 0, 19, 0),
    LATE_NIGHT_BREAK(21, 0, 22, 0);
    private BreakTimeRule breakTimeRule;

    BreakTimeRules(int startHour, int startMinute, int endHour, int endMinute) {
        EntryTime startTime = new EntryTime(new Hour(startHour), new Minute(startMinute));
        EntryTime endTime = new EntryTime(new Hour(endHour), new Minute(endMinute));
        breakTimeRule = new BreakTimeRule(startTime, endTime);
    }

    public BreakTimeRule getBreakTimeRule() {
        return breakTimeRule;
    }
}

