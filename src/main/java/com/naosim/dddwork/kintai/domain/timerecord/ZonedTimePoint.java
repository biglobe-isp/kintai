package com.naosim.dddwork.kintai.domain.timerecord;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.Value;

import java.time.LocalTime;
import java.time.ZonedDateTime;

@Value
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class ZonedTimePoint {
    ZonedDateTime zonedDateTime;

    public ZonedTimePoint(ZonedDateTime date, String hhmm) {
        if (date == null || hhmm == null) {
            throw new IllegalArgumentException("日付か時刻が取得できませんでした。");
        }
        //TODO: 24時以降も入力自体は受け付けられるようにして、サービス残業のトルツメを実装
        if (!isHHmm(hhmm)) {
            throw new IllegalArgumentException("不正な時刻です。");
        }

        this.zonedDateTime = date
                .plusHours(Integer.parseInt(hhmm.substring(0, 2)))
                .plusMinutes(Integer.parseInt(hhmm.substring(2)));
    }

    public ZonedTimePoint(ZonedDateTime date, LocalTime hhmm) {
        if (date == null || hhmm == null) {
            throw new IllegalArgumentException("日付か時刻が取得できませんでした。");
        }
        this.zonedDateTime = date
                .plusHours(hhmm.getHour())
                .plusMinutes(hhmm.getMinute());
    }

    private boolean isHHmm(String hhmm) {
        if (hhmm == null || hhmm.length() != 4) {
            return false;
        }
        return hhmm.matches("^([0-1][0-9]|2[0-3])([0-5][0-9])$");
    }

    public int getHour() {
        return this.zonedDateTime.getHour();
    }

    public int getMinute() {
        return this.zonedDateTime.getMinute();
    }

    public boolean isEqual(ZonedTimePoint comparison) {
        return this.zonedDateTime.isEqual(comparison.getZonedDateTime());
    }

    public boolean isBefore(ZonedTimePoint comparison) {
        return this.zonedDateTime.isBefore(comparison.getZonedDateTime());
    }

    public boolean isEqualOrBefore(ZonedTimePoint comparison) {
        return this.isEqual(comparison) || this.isBefore(comparison);
    }

    public boolean isAfter(ZonedTimePoint comparison) {
        return this.zonedDateTime.isAfter(comparison.getZonedDateTime());
    }

    public boolean isEqualOrAfter(ZonedTimePoint comparison) {
        return this.isEqual(comparison) || this.isAfter(comparison);
    }
}
