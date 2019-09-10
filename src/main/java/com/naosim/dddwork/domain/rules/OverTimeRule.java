package com.naosim.dddwork.domain.rules;

import com.naosim.dddwork.domain.AttendanceRecord;
import com.naosim.dddwork.domain.time.EntryTime;
import com.naosim.dddwork.domain.time.Hour;
import com.naosim.dddwork.domain.time.Minute;
import com.naosim.dddwork.domain.time.WorkingDuration;

public class OverTimeRule {
    private static final int FINAL_CUT_TIME_HOURS = 24;
    private static final int FINAL_CUT_TIME_MINUTES = 0;
    private static final EntryTime finalCutTime = new EntryTime(
            new Hour(FINAL_CUT_TIME_HOURS),
            new Minute(FINAL_CUT_TIME_MINUTES)
    );

    // Service Over Time Check
    public static AttendanceRecord adjustAttendanceRecord(AttendanceRecord attendanceRecord) {
        // in case of endTime of workingDuration is over 24:00 , it will be service overtime work(not counted.)
        // Overlapped check e.g. start 9:00 and end 02:00 , 2 hrs service over time work ( again not counted.)
        EntryTime startTime = attendanceRecord.getWorkingDuration().getStartTime();
        EntryTime endTime = attendanceRecord.getWorkingDuration().getEndTime();
        if (endTime.getValue() > finalCutTime.getValue() ||
                startTime.getValue() > endTime.getValue()) {
            EntryTime newEndTime = new EntryTime(
                    finalCutTime.getHour(),
                    finalCutTime.getMinute()
            );
            WorkingDuration workingDuration = new WorkingDuration(startTime, newEndTime);
            return new AttendanceRecord(attendanceRecord.getWorkingDate(), workingDuration);
        }
        return attendanceRecord;
    }
}
