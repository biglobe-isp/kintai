package com.naosim.dddwork.kintai.controller;

import lombok.NonNull;
import lombok.Value;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Value
class AttendanceInputParameter {

    LocalDate ymd;
    LocalTime startTime;
    LocalTime endTime;

    AttendanceInputParameter(@NonNull String ymd, @NonNull String startTime, @NonNull String endTime) {
        this.ymd = toZonedDateTime(ymd);
        this.startTime = toLocalTime(startTime);
        this.endTime = toLocalTime(endTime);
    }

    private LocalDate toZonedDateTime(String ymd) {
        try {
            return LocalDate.parse(ymd, DateTimeFormatter.ofPattern("yyyyMMdd"));
        } catch (Exception e) {
            throw new IllegalArgumentException("不正な日付です。");
        }
    }

    private LocalTime toLocalTime(String hhmm) {
        if (hhmm.length() != 4) {
            throw new IllegalArgumentException("不正な時刻です。");
        }
        // TODO: 24時以降の入力も受け付けられるようにするが、サービス残業とみなして23:59として打刻する
        return LocalTime.parse(hhmm, DateTimeFormatter.ofPattern("HHmm"));
    }
}
