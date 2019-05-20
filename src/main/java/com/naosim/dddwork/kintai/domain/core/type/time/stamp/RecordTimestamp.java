package com.naosim.dddwork.kintai.domain.core.type.time.stamp;

import java.time.LocalDateTime;


/**
 * 記録時刻
 */
public class RecordTimestamp {

    final LocalDateTime dateTime;


    /* 生成 */

    private RecordTimestamp(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public static RecordTimestamp now() {
        return new RecordTimestamp(LocalDateTime.now());
    }


    /* 値 */

    public String storedValue() {
        return dateTime.toString();
    }
}
