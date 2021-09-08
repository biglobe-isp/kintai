package com.naosim.dddwork.kintai.domain.timerecord;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.Value;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;

@Value
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class EndTime {

    ZonedTimePoint timePoint;

    public EndTime(LocalDate attendanceDate, LocalTime endTime) {
        if (attendanceDate == null || endTime == null) {
            throw new IllegalArgumentException("出勤日または終了時間がありません。");
        }
        this.timePoint = new ZonedTimePoint(attendanceDate.atStartOfDay(ZoneId.systemDefault()), endTime);
    }
}
