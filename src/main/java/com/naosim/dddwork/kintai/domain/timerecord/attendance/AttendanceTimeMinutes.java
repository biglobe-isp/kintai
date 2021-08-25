package com.naosim.dddwork.kintai.domain.timerecord.attendance;

import com.naosim.dddwork.kintai.domain.timerecord.TimeUnits;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.Value;

@Value
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class AttendanceTimeMinutes {

    int minutes;

    public AttendanceTimeMinutes(AttendanceTimeInterval attendanceTimeInterval) {
        if (attendanceTimeInterval == null) {
            throw new IllegalArgumentException("出勤時間の開始終了の情報がありません。");
        }
        this.minutes = attendanceTimeInterval.between(TimeUnits.MINUTES);
    }
}
