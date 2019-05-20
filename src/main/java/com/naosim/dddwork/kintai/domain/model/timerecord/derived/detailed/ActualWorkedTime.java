package com.naosim.dddwork.kintai.domain.model.timerecord.derived.detailed;

import com.naosim.dddwork.kintai.domain.core.type.time.amount.AmountOfMinutes;
import com.naosim.dddwork.kintai.domain.model.foundation.time.range.WorkTimeRange;
import com.naosim.dddwork.kintai.domain.model.regulation.WorkTimeCalculation;


/**
 * 実質労働時間
 * <pre>
 *     実質労働時間 = 拘束時間 - サービス残業時間
 * </pre>
 */
public class ActualWorkedTime {

    final AmountOfMinutes actualWorkedMinutes;


    /* 生成 */

    private ActualWorkedTime(WorkTimeRange spentTimeRange) {

        /* 実質労働時間（分）は拘束時間がわかればレギュレーションが計算してくれる */
        actualWorkedMinutes = WorkTimeCalculation.actualTotalWorkMinutesIn(spentTimeRange);
    }

    public static ActualWorkedTime of(WorkTimeRange spentTimeRange) {
        return new ActualWorkedTime(spentTimeRange);
    }


    /* 計算 */

    public AmountOfMinutes minus(ActualUnpaidWorkedTime actualUnpaidWorkedTime) {
        return actualWorkedMinutes.minus(actualUnpaidWorkedTime.minutes);
    }
}
