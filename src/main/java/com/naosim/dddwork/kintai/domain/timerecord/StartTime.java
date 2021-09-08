package com.naosim.dddwork.kintai.domain.timerecord;

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

    public StartTime(LocalDate attendanceDate, LocalTime startTime) {
        if (attendanceDate == null || startTime == null) {
            throw new IllegalArgumentException("出勤日または開始時間がありません。");
        }
        this.timePoint = new ZonedTimePoint(attendanceDate.atStartOfDay(ZoneId.systemDefault()), startTime);
    }
}
