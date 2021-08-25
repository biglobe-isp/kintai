package com.naosim.dddwork.kintai.domain.timerecord.breaktime;

import com.naosim.dddwork.kintai.domain.timerecord.attendance.AttendanceTimeInterval;
import com.naosim.dddwork.kintai.domain.timerecord.regulation.RegulatedBreakTimeShift;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.Value;

import static com.naosim.dddwork.kintai.domain.timerecord.TimeUnits.MINUTES;

@Value
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class ActualBreakTimeMinutes {

    int minutes;

    public ActualBreakTimeMinutes(AttendanceTimeInterval attendanceTimeInterval, RegulatedBreakTimeShift regulatedBreakTimeShift) {
        if (attendanceTimeInterval == null || regulatedBreakTimeShift == null) {
            throw new IllegalArgumentException("労働時間、または休憩時間が取得できませんでした。");
        }
        this.minutes = sumIntersection(attendanceTimeInterval, regulatedBreakTimeShift);
    }

    private int sumIntersection(AttendanceTimeInterval attendanceTimeInterval, RegulatedBreakTimeShift regulatedBreakTimeShift) {
        return regulatedBreakTimeShift
                .getIntervals()
                .stream()
                .map((regulatedBreakTimeInterval) -> attendanceTimeInterval.intersect(regulatedBreakTimeInterval, MINUTES))
                .reduce( 0, (minutes, additionalMinutes) -> minutes + additionalMinutes);
    }
}
