package com.naosim.dddwork.kintai.domain.timerecord.attendance;

import com.naosim.dddwork.kintai.domain.timerecord.TimeLength;
import com.naosim.dddwork.kintai.domain.timerecord.breaktime.ActualBreakTimeMinutes;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.Value;

import static com.naosim.dddwork.kintai.domain.timerecord.TimeUnits.MINUTES;

@Value
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class AttendanceTimeMinutes {

    TimeLength length;

    public AttendanceTimeMinutes(AttendanceTimeInterval attendanceTimeInterval) {
        if (attendanceTimeInterval == null) {
            throw new IllegalArgumentException("出勤時間の開始終了の情報がありません。");
        }
        // 共通と業務の線引き
        this.length = attendanceTimeInterval.between(MINUTES);
    }

    public TimeLength subtract(ActualBreakTimeMinutes actualBreakTimeMinutes) {
        return this.length.subtract(actualBreakTimeMinutes.getLength());
    }
}
