package com.naosim.dddwork.api.attendancetime;

import com.naosim.dddwork.domain.attendance.EndTime;
import com.naosim.dddwork.domain.attendance.StartTime;
import com.naosim.dddwork.exception.ValidationException;
import lombok.Value;

@Value
public class VerifiedAttendanceTime {
    StartTime startTime;
    EndTime endTime;

    public static VerifiedAttendanceTime of(NotVerifiedAttendanceTime notVerifiedAttendanceTime) throws ValidationException {
        if (isStartGreaterThanEnd(notVerifiedAttendanceTime)) {
            throw new ValidationException("開始時刻＞終了時刻は登録できません");
        }
        return new VerifiedAttendanceTime(notVerifiedAttendanceTime.getStartTime(),
                                           notVerifiedAttendanceTime.getEndTime());
    }

    private static boolean isStartGreaterThanEnd(NotVerifiedAttendanceTime notVerifiedAttendanceTime) {
        int startTime = notVerifiedAttendanceTime.getStartTime().getTimePoint().getMinutesValue();
        int endTime = notVerifiedAttendanceTime.getEndTime().getTimePoint().getMinutesValue();

        return startTime > endTime;
    }
}
