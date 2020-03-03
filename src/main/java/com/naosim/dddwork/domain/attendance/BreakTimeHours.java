package com.naosim.dddwork.domain.attendance;

import com.naosim.dddwork.domain.TimeRange;
import com.naosim.dddwork.domain.TimeUnit;
import com.naosim.dddwork.domain.workregulations.WorkRegulations;
import lombok.Value;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Value
public class BreakTimeHours {
    TimeUnit timeUnit;

    public static BreakTimeHours of(AttendanceTime attendanceTime, WorkRegulations workRegulations) {
        return new BreakTimeHours(attendanceTime, workRegulations);
    }

    private BreakTimeHours(AttendanceTime attendanceTime, WorkRegulations workRegulations){
        List<TimeRange> list = workRegulations.getBreakTimes().getList();

        int breakTimeMinutes = 0;
        for (TimeRange timeRange : list) {
            breakTimeMinutes += getIncludedBreakTime(attendanceTime, timeRange);
        }

        this.timeUnit = TimeUnit.of(breakTimeMinutes);
    }

    private int getIncludedBreakTime(AttendanceTime attendanceTime, TimeRange breakTimeRange) {
        LocalTime breakStartTime = LocalTime.of(breakTimeRange.getTimeFrom().getHour(),
                                                breakTimeRange.getTimeFrom().getMinutes());
        LocalTime breakEndTime = LocalTime.of(breakTimeRange.getTimeTo().getHour(),
                                              breakTimeRange.getTimeTo().getMinutes());

        LocalTime attendanceStartTime = LocalTime.of(attendanceTime.getStartTime().getTimePoint().getHour(),
                                                     attendanceTime.getStartTime().getTimePoint().getMinutes());
        LocalTime attendanceEndTime = attendanceTime.getEndTime().getLastTimeOnTheDay();

        if (attendanceEndTime.compareTo(breakStartTime) < 0) {
            return 0;
        }

        LocalTime targetStartTime;
        if (attendanceStartTime.compareTo(breakStartTime) > 0) {
            targetStartTime = attendanceStartTime;
        } else {
            targetStartTime = breakStartTime;
        }

        LocalTime targetEndTime;
        if (attendanceEndTime.compareTo(breakEndTime) > 0) {
            targetEndTime = breakEndTime;
        } else {
            targetEndTime = attendanceEndTime;
        }

        return (int) ChronoUnit.MINUTES.between(targetStartTime, targetEndTime);
    }
}
