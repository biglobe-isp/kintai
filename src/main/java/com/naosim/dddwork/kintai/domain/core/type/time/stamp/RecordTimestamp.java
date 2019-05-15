package com.naosim.dddwork.kintai.domain.core.type.time.stamp;

import java.time.LocalDateTime;


/**
 * 記録時刻
 */
public class RecordTimestamp {

    final LocalDateTime dateTime;


    public static RecordTimestamp now() {
        return new RecordTimestamp(LocalDateTime.now());
    }

    public RecordTimestamp(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }


    public String storedValue() {
        return dateTime.toString();
    }
}
