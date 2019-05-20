package com.naosim.dddwork.kintai.domain.model.timerecord.derived.detailed;

import com.naosim.dddwork.kintai.domain.core.type.time.amount.AmountOfMinutes;
import com.naosim.dddwork.kintai.domain.model.foundation.time.range.WorkTimeRange;
import com.naosim.dddwork.kintai.domain.model.regulation.WorkTimeCalculation;


/**
 * サービス残業時間
 */
public class ActualUnpaidWorkedTime {

    final AmountOfMinutes minutes;


    /* 生成 */

    private ActualUnpaidWorkedTime(WorkTimeRange spentTimeRange) {

        /* サービス残業時間（分）は拘束時間がわかればレギュレーションが計算してくれる */
        minutes = WorkTimeCalculation.actualTotalUnpaidOvertimeIn(spentTimeRange);
    }

    public static ActualUnpaidWorkedTime of(WorkTimeRange spentTimeRange) {
        return new ActualUnpaidWorkedTime(spentTimeRange);
    }

}
