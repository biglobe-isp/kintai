package com.naosim.dddwork.domain.attendance;

import com.naosim.dddwork.domain.TimeRange;
import com.naosim.dddwork.domain.TimeUnit;
import com.naosim.dddwork.domain.attendance.attendancetime.VerifiedAttendanceTime;
import com.naosim.dddwork.domain.workregulations.WorkRegulations;
import lombok.Value;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Value
public class BreakTimeHours {
    TimeUnit timeUnit;

    public static BreakTimeHours of(VerifiedAttendanceTime attendanceTime, WorkRegulations workRegulations) {
        return new BreakTimeHours(attendanceTime, workRegulations);
    }

    private BreakTimeHours(VerifiedAttendanceTime attendanceTime, WorkRegulations workRegulations){
        List<TimeRange> list = workRegulations.getBreakTimes().getList();

        int breakTimeMinutes = 0;
        for (TimeRange timeRange : list) {
            breakTimeMinutes += getIncludedBreakTime(attendanceTime, timeRange);
        }

        this.timeUnit = TimeUnit.of(breakTimeMinutes);
    }

    private int getIncludedBreakTime(VerifiedAttendanceTime attendanceTime, TimeRange breakTimeRange) {
        LocalTime breakStartTime = breakTimeRange.getTimeFrom().getLocalTime();
        LocalTime breakEndTime = breakTimeRange.getTimeTo().getLocalTime();
        LocalTime attendanceStartTime = attendanceTime.getStartTime().getTimePoint().getLocalTime();
        LocalTime attendanceEndTime = attendanceTime.getEndTime().getLastTimeOnTheDay();

        if (attendanceEndTime.compareTo(breakStartTime) < 0) {
            return 0;
        }

        LocalTime targetStartTime = min(attendanceStartTime, breakStartTime);
        LocalTime targetEndTime = max(attendanceEndTime, breakEndTime);

        return (int) ChronoUnit.MINUTES.between(targetStartTime, targetEndTime);
    }

    private LocalTime min(LocalTime localTime1, LocalTime localTime2) {
        if (localTime1.compareTo(localTime2) > 0) {
            return localTime1;
        } else {
            return localTime2;
        }
    }

    private LocalTime max(LocalTime localTime1, LocalTime localTime2) {
        if (localTime1.compareTo(localTime2) > 0) {
            return localTime2;
        } else {
            return localTime1;
        }
    }
}
