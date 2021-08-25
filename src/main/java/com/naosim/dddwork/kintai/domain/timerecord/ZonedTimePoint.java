package com.naosim.dddwork.kintai.domain.timerecord;

import lombok.Value;

import java.time.ZonedDateTime;

@Value
public class ZonedTimePoint {
    ZonedDateTime zonedDateTime;


    public ZonedTimePoint(ZonedDateTime zonedDateTime) {
        this.zonedDateTime = zonedDateTime;
    }

    public ZonedTimePoint(ZonedDateTime date, String hhmm) throws Exception {
        if (date == null) {
            throw new IllegalArgumentException("不正な値です。");
        }
        if (hhmm == null || "".equals(hhmm)) {
            throw new IllegalArgumentException("不正な値です。");
        }
        if (hhmm.length() != 4) {
            throw new IllegalArgumentException("不正な値です。");
        }
        // TODO: 時刻チェック
        this.zonedDateTime = date
                .plusHours(Integer.parseInt(hhmm.substring(0, 2)))
                .plusMinutes(Integer.parseInt(hhmm.substring(2)));
    }

    public boolean isEqual(ZonedTimePoint comparison) {
        return this.zonedDateTime.isEqual(comparison.getZonedDateTime()) || this.zonedDateTime.isAfter(comparison.getZonedDateTime());
    }

    public boolean isBefore(ZonedTimePoint comparison) {
        return this.zonedDateTime.isBefore(comparison.getZonedDateTime());
    }

    public boolean isEqualOrBefore(ZonedTimePoint comparison) {
        return this.zonedDateTime.isEqual(comparison.getZonedDateTime()) || this.zonedDateTime.isBefore(comparison.getZonedDateTime());
    }

    public boolean isAfter(ZonedTimePoint comparison) {
        return this.zonedDateTime.isAfter(comparison.getZonedDateTime());
    }

    public boolean isEqualOrAfter(ZonedTimePoint comparison) {
        return this.zonedDateTime.isEqual(comparison.getZonedDateTime()) || this.zonedDateTime.isAfter(comparison.getZonedDateTime());
    }
}
