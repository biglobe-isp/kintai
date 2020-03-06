package com.naosim.dddwork.domain.attendance.attendancetime;

import com.naosim.dddwork.domain.attendance.EndTime;
import com.naosim.dddwork.domain.attendance.StartTime;
import lombok.Value;

@Value
public class VerifiedAttendanceTime {
    StartTime startTime;
    EndTime endTime;

    public static VerifiedAttendanceTime of(NotVerifiedAttendanceTime notVerifiedAttendanceTime) {
        if (isStartGreaterThanEnd(notVerifiedAttendanceTime)) {
            throw new RuntimeException("開始時刻＞終了時刻は登録できません");
        }
        return new VerifiedAttendanceTime(notVerifiedAttendanceTime.getStartTime(),
                                           notVerifiedAttendanceTime.getEndTime());
    }

    private static boolean isStartGreaterThanEnd(NotVerifiedAttendanceTime notVerifiedAttendanceTime) {
        int startTime = notVerifiedAttendanceTime.getStartTime().getTimePoint().getIntValue();
        int endTime = notVerifiedAttendanceTime.getEndTime().getTimePoint().getIntValue();

        return startTime > endTime;
    }
}
