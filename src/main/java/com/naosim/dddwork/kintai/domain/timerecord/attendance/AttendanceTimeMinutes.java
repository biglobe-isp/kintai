package com.naosim.dddwork.kintai.domain.timerecord.attendance;

import com.naosim.dddwork.kintai.domain.timerecord.TimeLength;
import com.naosim.dddwork.kintai.domain.timerecord.regulation.RegulatedBreakTimeShift;
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
        this.length = attendanceTimeInterval.between(MINUTES);
    }

    public TimeLength excludeBreakTime(
            AttendanceTimeInterval attendanceTimeInterval,
            RegulatedBreakTimeShift regulatedBreakTimeShift) {
        return regulatedBreakTimeShift
                .getIntervals()
                .stream()
                .map((regulatedBreakTimeInterval) -> attendanceTimeInterval.intersect(
                        regulatedBreakTimeInterval,
                        MINUTES
                ))
                .reduce(this.length, TimeLength::subtract);
    }
}
