package com.naosim.dddwork.domain.attendance;

import com.naosim.dddwork.domain.TimeRange;
import com.naosim.dddwork.domain.TimeUnit;
import com.naosim.dddwork.domain.workregulations.WorkRegulations;
import lombok.Value;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Value
public class BreakTimeHours {
    TimeUnit timeUnit;

    public static BreakTimeHours of(AttendanceTime attendanceTime, WorkRegulations workRegulations) {
        return new BreakTimeHours(attendanceTime, workRegulations);
    }

    private BreakTimeHours(AttendanceTime attendanceTime, WorkRegulations workRegulations){
        List<TimeRange> list = new ArrayList<>();
        list.add(workRegulations.getBreakTimes().getLunchBreakTime());
        list.add(workRegulations.getBreakTimes().getEveningBreakTime());
        list.add(workRegulations.getBreakTimes().getNightBreakTime());

        int breakTimeMinutes = 0;
        for (TimeRange timeRange : list) {
            breakTimeMinutes += getIncludedBreakTime(attendanceTime, timeRange);
        }

        this.timeUnit = TimeUnit.of(breakTimeMinutes);
    }

    private int getIncludedBreakTime(AttendanceTime attendanceTime, TimeRange breakTime) {
        LocalTime endTime;
        if (attendanceTime.getEndTime().isExceedLimitTime()) {
            endTime = LocalTime.MAX;
        } else {
            endTime = attendanceTime.getEndTime().getTimePoint().getLocalTime();
        }

        // 遅刻NGのため終了時刻のみで判断する
        if (endTime.getHour() == breakTime.getTimeFrom().getHour()) {
            return endTime.getMinute();
        } else if(endTime.getHour() >= breakTime.getTimeTo().getHour()) {
            return breakTime.getRangeMinutes();
        }
        return 0;
    }
}
