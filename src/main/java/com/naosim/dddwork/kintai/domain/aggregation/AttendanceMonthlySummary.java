package com.naosim.dddwork.kintai.domain.aggregation;

import com.naosim.dddwork.kintai.domain.timerecord.TimeLength;
import com.naosim.dddwork.kintai.domain.timerecord.attendance.AttendanceRecords;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.Value;

import static com.naosim.dddwork.kintai.domain.timerecord.TimeUnits.MINUTES;

@Value
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class AttendanceMonthlySummary {
    TimeLength totalWorkingTimeMinutesLength;
    TimeLength totalOvertimeMinutesLength;

    public AttendanceMonthlySummary(AttendanceRecords attendanceRecords) {
        if (attendanceRecords == null) {
            throw new IllegalArgumentException("出勤記録がありません。");
        }
        //TODO: リファクタ
        this.totalWorkingTimeMinutesLength = attendanceRecords
                .getAttendanceRecords()
                .stream()
                .map(rec -> new TimeLength(rec.getActualWorkingTimeMinutesLength(), MINUTES))
                .reduce(TimeLength::add)
                .orElse(new TimeLength(0, MINUTES));
        this.totalOvertimeMinutesLength = attendanceRecords
                .getAttendanceRecords()
                .stream()
                .map(rec -> new TimeLength(rec.getActualOvertimeMinutesLength(), MINUTES))
                .reduce(TimeLength::add)
                .orElse(new TimeLength(0, MINUTES));
    }

    //HACK: ActualMinutesでも同じ関数を作っており、冗長になっている
    public int convertActualWorkingTimeMinutesToHour() {
        return (int) this.totalWorkingTimeMinutesLength.minuteToHour().getLength();
    }

    public int convertActualOvertimeMinutesToHour() {
        return (int) this.totalOvertimeMinutesLength.minuteToHour().getLength();
    }

    public int extractRemainderActualWorkingTimeMinutes() {
        return (int) this.totalWorkingTimeMinutesLength.extractRemainderMinutes().getLength();
    }

    public int extractRemainderActualOvertimeMinutes() {
        return (int) this.totalOvertimeMinutesLength.extractRemainderMinutes().getLength();
    }
}
