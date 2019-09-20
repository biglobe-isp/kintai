package com.naosim.dddwork.domain.rules;

import com.naosim.dddwork.domain.AdjustedAttendanceRecord;
import com.naosim.dddwork.domain.AttendanceRecord;
import com.naosim.dddwork.domain.time.EntryTime;
import com.naosim.dddwork.domain.time.Hour;
import com.naosim.dddwork.domain.time.Minute;

public class OverTimeRule {
    private static final int FINAL_CUT_TIME_HOURS = 24;
    private static final int FINAL_CUT_TIME_MINUTES = 0;
    private static final EntryTime finalCutTime = new EntryTime(
            new Hour(FINAL_CUT_TIME_HOURS),
            new Minute(FINAL_CUT_TIME_MINUTES)
    );

    // Service Over Time Check
    public AdjustedAttendanceRecord adjustAttendanceRecord(AttendanceRecord attendanceRecord) {
        // in case of endTime of workingDuration is over 24:00 , it will be service overtime work(not counted.)
        // Overlapped check e.g. start 9:00 and end 02:00 , 2 hrs service over time work ( again not counted.)
        int startTimeValue = attendanceRecord.getStartTimeValue();
        int endTimeValue = attendanceRecord.getEndTimeValue();
        if (endTimeValue > finalCutTime.getValue() ||
                startTimeValue > endTimeValue) {
            EntryTime newEndTime = new EntryTime(
                    finalCutTime.getHour(),
                    finalCutTime.getMinute()
            );
            return new AdjustedAttendanceRecord(
                    attendanceRecord.getWorkingDate(),
                    attendanceRecord.getStartTime(),
                    newEndTime
            );
        }
        return new AdjustedAttendanceRecord(
                attendanceRecord.getWorkingDate(),
                attendanceRecord.getStartTime(),
                attendanceRecord.getEndTime()
        );
    }
}
