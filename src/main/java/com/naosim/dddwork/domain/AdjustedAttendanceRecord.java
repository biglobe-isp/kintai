package com.naosim.dddwork.domain;

import com.naosim.dddwork.domain.date.WorkingDate;
import com.naosim.dddwork.domain.time.EntryTime;

public class AdjustedAttendanceRecord extends AttendanceRecord {
    public AdjustedAttendanceRecord(
            WorkingDate workingDate,
            EntryTime startTime, EntryTime endTime) {
        super(workingDate, startTime, endTime);
    }
}
