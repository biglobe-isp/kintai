package com.naosim.dddwork.kintai.domain.timerecord;

import com.naosim.dddwork.kintai.domain.timerecord.attendance.AttendanceDate;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.Value;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;

@Value
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class StartTime {

    ZonedTimePoint timePoint;

    // TODO: Stringで受け付けるのはおかしい。Controller層でバリデーションしてからインスタンス化する
    public StartTime(AttendanceDate attendanceDate, String startTime) {
        if (attendanceDate == null || startTime == null) {
            throw new IllegalArgumentException("出勤日または開始時間がありません。");
        }
        this.timePoint = new ZonedTimePoint(attendanceDate.getZonedDateTime(), startTime);
    }

    public StartTime(LocalDate attendanceDate, LocalTime startTime) {
        if (attendanceDate == null || startTime == null) {
            throw new IllegalArgumentException("出勤日または開始時間がありません。");
        }
        this.timePoint = new ZonedTimePoint(attendanceDate.atStartOfDay(ZoneId.systemDefault()), startTime);
    }
}
