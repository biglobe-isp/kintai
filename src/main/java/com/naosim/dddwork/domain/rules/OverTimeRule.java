package com.naosim.dddwork.domain.rules;

import com.naosim.dddwork.domain.time.Hour;
import com.naosim.dddwork.domain.time.Minute;
import com.naosim.dddwork.domain.time.EntryTime;
import com.naosim.dddwork.domain.time.WorkingDuration;

public class OverTimeRule {

    private final int FinalCutHour = 24;
    private final int FinalCutMinutes = 0;

    private final EntryTime finalCutTime;

    public OverTimeRule()
    {
        finalCutTime = new EntryTime( new Hour(FinalCutHour), new Minute(FinalCutMinutes));
    }

    // Service Over Time
    public WorkingDuration checkServiceOverTime(WorkingDuration workingDuration)
    {
        // in case of endTime of workingDuration is over 24:00 , it will be service overtime work(not counted.)
        // Overlapped check e.g. start 9:00 and end 02:00 , 2 hrs service over time
        EntryTime startTime = workingDuration.getStartTime();
        EntryTime endTime = workingDuration.getEndTime();
        if(endTime.getValue() > finalCutTime.getValue() ||
                startTime.getValue() > endTime.getValue())
        {
            EntryTime newEndTime  = new EntryTime(finalCutTime.getHour(), finalCutTime.getMinute());
            return new WorkingDuration(workingDuration.getStartTime(), newEndTime);
        }
        return workingDuration;
    }

}
