package com.naosim.dddwork.kintai.domain.model.timerecord.derived.detailed;

import com.naosim.dddwork.kintai.domain.core.type.time.amount.AmountOfMinutes;


/**
 * 賃金発生労働時間
 * <pre>
 *     賃金発生労働時間 = 実質労働時間 - サービス残業時間
 * </pre>
 */
public class ActualPaidWorkedTime {

    final AmountOfMinutes actualPaidWorkMinutes;


    /* 生成 */

    private ActualPaidWorkedTime(ActualWorkedTime actualWorkedTime, ActualUnpaidWorkedTime actualUnpaidWorkedTime) {

        /* 賃金発生労働時間（分） = 実際の労働時間 - サービス残業時間 */
        actualPaidWorkMinutes = actualWorkedTime.minus(actualUnpaidWorkedTime);
    }

    public static ActualPaidWorkedTime of(ActualWorkedTime actualWorkedTime, ActualUnpaidWorkedTime actualUnpaidWorkedTime) {
        return new ActualPaidWorkedTime(actualWorkedTime, actualUnpaidWorkedTime);
    }


    /* 演算 */

    public AmountOfMinutes minus(AmountOfMinutes amountOfMinutes) {
        return actualPaidWorkMinutes.minus(amountOfMinutes);
    }
}
