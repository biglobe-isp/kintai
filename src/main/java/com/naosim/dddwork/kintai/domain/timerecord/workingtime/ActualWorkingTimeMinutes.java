package com.naosim.dddwork.kintai.domain.timerecord.workingtime;

import com.naosim.dddwork.kintai.domain.timerecord.attendance.AttendanceTimeMinutes;
import com.naosim.dddwork.kintai.domain.timerecord.breaktime.ActualBreakTimeMinutes;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.Value;

@Value
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class ActualWorkingTimeMinutes {

    int minutes;

    public ActualWorkingTimeMinutes(AttendanceTimeMinutes attendanceTimeMinutes, ActualBreakTimeMinutes actualBreakTimeMinutes) throws Exception {
        this.minutes = minusBreakTime(attendanceTimeMinutes, actualBreakTimeMinutes);
        if (this.minutes < 0) {
            throw new Exception("労働時間が不正です。マイナスの値が入っています。");
        }
    }

    private int minusBreakTime(AttendanceTimeMinutes attendanceTimeMinutes, ActualBreakTimeMinutes actualBreakTimeMinutes) {
        return attendanceTimeMinutes.getMinutes() - actualBreakTimeMinutes.getMinutes();
    }
}
