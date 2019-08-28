package com.naosim.dddwork.domain.rules;

import com.naosim.dddwork.domain.time.EntryTime;
import com.naosim.dddwork.domain.time.Hour;
import com.naosim.dddwork.domain.time.Minute;
import com.naosim.dddwork.domain.time.WorkingDuration;

// TODO make this class to enum
public enum OverTimeRule {


    FINAL_CUT_TIME(24,00);

    private EntryTime entryTime;
    OverTimeRule(int hour, int minute) {
        entryTime = new EntryTime(new Hour(hour),new Minute(minute));
    }
    public EntryTime getEntryTime()
    {
        return entryTime;
    }

    // Service Over Time Check
    public static WorkingDuration checkServiceOverTime(WorkingDuration workingDuration)
    {
        // in case of endTime of workingDuration is over 24:00 , it will be service overtime work(not counted.)
        // Overlapped check e.g. start 9:00 and end 02:00 , 2 hrs service over time
        EntryTime startTime = workingDuration.getStartTime();
        EntryTime endTime = workingDuration.getEndTime();
        if(endTime.getValue() > FINAL_CUT_TIME.getEntryTime().getValue() ||
                startTime.getValue() > endTime.getValue())
        {
            EntryTime newEndTime  = new EntryTime(FINAL_CUT_TIME.getEntryTime().getHour(), FINAL_CUT_TIME.getEntryTime().getMinute());
            return new WorkingDuration(workingDuration.getStartTime(), newEndTime);
        }
        return workingDuration;
    }


}
