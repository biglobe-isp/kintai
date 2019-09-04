package com.naosim.dddwork.domain.rules;

import com.naosim.dddwork.domain.AttendanceRecord;
import com.naosim.dddwork.domain.time.EntryTime;
import com.naosim.dddwork.domain.time.Hour;
import com.naosim.dddwork.domain.time.Minute;
import com.naosim.dddwork.domain.time.WorkingDuration;

public enum OverTimeRule {
    FINAL_CUT_TIME(24, 0);
    private EntryTime entryTime;

    OverTimeRule(int hour, int minute) {
        entryTime = new EntryTime(new Hour(hour), new Minute(minute));
    }

    public EntryTime getEntryTime() {
        return entryTime;
    }

    // Service Over Time Check
    public static AttendanceRecord adjustAttendanceRecord(AttendanceRecord attendanceRecord) {
        // in case of endTime of workingDuration is over 24:00 , it will be service overtime work(not counted.)
        // Overlapped check e.g. start 9:00 and end 02:00 , 2 hrs service over time work ( again not counted.)
        EntryTime startTime = attendanceRecord.getWorkingDuration().getStartTime();
        EntryTime endTime = attendanceRecord.getWorkingDuration().getEndTime();
        if (endTime.getValue() > FINAL_CUT_TIME.getEntryTime().getValue() ||
                startTime.getValue() > endTime.getValue()) {
            EntryTime newEndTime = new EntryTime(
                    FINAL_CUT_TIME.getEntryTime().getHour(),
                    FINAL_CUT_TIME.getEntryTime().getMinute()
            );
            WorkingDuration workingDuration = new WorkingDuration(startTime, newEndTime);
            return new AttendanceRecord(attendanceRecord.getWorkingDate(), workingDuration);
        }
        return attendanceRecord;
    }
}
