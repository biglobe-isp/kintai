package com.naosim.dddwork.kintai.domain.timerecord.breaktime;

import com.naosim.dddwork.kintai.domain.timerecord.TimeLength;
import com.naosim.dddwork.kintai.domain.timerecord.attendance.AttendanceTimeInterval;
import com.naosim.dddwork.kintai.domain.timerecord.regulation.RegulatedBreakTimeShift;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.Value;

import static com.naosim.dddwork.kintai.domain.timerecord.TimeUnits.MINUTES;

@Value
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class ActualBreakTimeMinutes {

    TimeLength length;

    public ActualBreakTimeMinutes(AttendanceTimeInterval attendanceTimeInterval, RegulatedBreakTimeShift regulatedBreakTimeShift) {
        if (attendanceTimeInterval == null || regulatedBreakTimeShift == null) {
            throw new IllegalArgumentException("労働時間、または休憩時間が取得できませんでした。");
        }
        this.length = sumIntersection(attendanceTimeInterval, regulatedBreakTimeShift);
    }

    private TimeLength sumIntersection(AttendanceTimeInterval attendanceTimeInterval, RegulatedBreakTimeShift regulatedBreakTimeShift) {
        return regulatedBreakTimeShift
                .getIntervals()
                .stream()
                .map((regulatedBreakTimeInterval) -> {
                    // TODO: エラーハンドリングをどうにかする
                    try {
                        return attendanceTimeInterval.intersect(regulatedBreakTimeInterval, MINUTES);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return null;
                })
                .reduce(new TimeLength(0, MINUTES), TimeLength::add);
    }
}
