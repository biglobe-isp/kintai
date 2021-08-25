package com.naosim.dddwork.kintai.domain.timerecord.attendance;

import com.naosim.dddwork.kintai.domain.timerecord.TimeLength;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.Value;

import static com.naosim.dddwork.kintai.domain.timerecord.TimeUnits.MINUTES;

@Value
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class AttendanceTimeMinutes {

    TimeLength minutes;

    public AttendanceTimeMinutes(AttendanceTimeInterval attendanceTimeInterval) {
        if (attendanceTimeInterval == null) {
            throw new IllegalArgumentException("出勤時間の開始終了の情報がありません。");
        }
        this.minutes = attendanceTimeInterval.between(MINUTES);
    }
}
