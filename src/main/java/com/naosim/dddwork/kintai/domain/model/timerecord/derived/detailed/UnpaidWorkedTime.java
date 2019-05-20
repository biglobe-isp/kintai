package com.naosim.dddwork.kintai.domain.model.timerecord.derived.detailed;

import com.naosim.dddwork.kintai.domain.core.type.time.amount.AmountOfMinutes;


/**
 * サービス残業時間
 */
public class UnpaidWorkedTime {

    final AmountOfMinutes minutes;


    /* 生成 */

    private UnpaidWorkedTime(AmountOfMinutes minutes) {
        this.minutes = minutes;
    }

    public static UnpaidWorkedTime of(int minutes) {
        return UnpaidWorkedTime.of(AmountOfMinutes.of(minutes));
    }

    public static UnpaidWorkedTime of(AmountOfMinutes minutes) {
        return new UnpaidWorkedTime(minutes);
    }

}
