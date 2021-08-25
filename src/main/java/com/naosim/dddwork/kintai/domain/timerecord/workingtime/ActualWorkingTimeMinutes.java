package com.naosim.dddwork.kintai.domain.timerecord.workingtime;

import com.naosim.dddwork.kintai.domain.timerecord.TimeLength;
import com.naosim.dddwork.kintai.domain.timerecord.attendance.AttendanceTimeMinutes;
import com.naosim.dddwork.kintai.domain.timerecord.breaktime.ActualBreakTimeMinutes;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.Value;

@Value
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class ActualWorkingTimeMinutes {

    TimeLength minutes;

    public ActualWorkingTimeMinutes(AttendanceTimeMinutes attendanceTimeMinutes, ActualBreakTimeMinutes actualBreakTimeMinutes) {
        this.minutes = subtractBreakTime(attendanceTimeMinutes, actualBreakTimeMinutes);
    }

    public int intValue() {
        return (int)this.minutes.getLength();
    }
    private TimeLength subtractBreakTime(AttendanceTimeMinutes attendanceTimeMinutes, ActualBreakTimeMinutes actualBreakTimeMinutes) {
        return attendanceTimeMinutes.getMinutes().subtract(actualBreakTimeMinutes.getMinutes());
    }
}
