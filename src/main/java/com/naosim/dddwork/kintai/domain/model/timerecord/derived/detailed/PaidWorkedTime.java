package com.naosim.dddwork.kintai.domain.model.timerecord.derived.detailed;


import com.naosim.dddwork.kintai.domain.core.type.time.amount.AmountOfMinutes;

import static com.naosim.dddwork.kintai.domain.model.regulation.WorkingTimeRegulations.LEGAL_WORKING_MINUTES;


/**
 * 労働時間（賃金発生分）
 */
public class PaidWorkedTime {

    /** 通常労働時間 */
    final WorkedTimeAsRegular workedTimeAsRegular;
    /** 残業時間 */
    final WorkedTimeAsOvertime workedTimeAsOvertime;


    /* 生成 */

    /**
     * 賃金が発生する実際の労働時間量（分）で構築する．
     *
     * @param actualPaidWorkMinutes 賃金が発生する実際の労働時間量（分）
     */
    private PaidWorkedTime(AmountOfMinutes actualPaidWorkMinutes) {

        /* 残業時間(分) = 賃金発生労働時間量 - 法定労働時間 */
        final AmountOfMinutes actualPaidOvertimeMinutes = actualPaidWorkMinutes.minus(LEGAL_WORKING_MINUTES);
        /* 通常労働時間(分) = min(賃金が発生する実際の労働時間量, 法定労働時間) */
        final AmountOfMinutes actualRegularWorkTime = AmountOfMinutes.findMinimum(actualPaidWorkMinutes, LEGAL_WORKING_MINUTES);

        this.workedTimeAsRegular = WorkedTimeAsRegular.of(actualRegularWorkTime);
        this.workedTimeAsOvertime = WorkedTimeAsOvertime.of(actualPaidOvertimeMinutes);
    }

    public static PaidWorkedTime of(AmountOfMinutes actualPaidWorkMinutes) {
        return new PaidWorkedTime(actualPaidWorkMinutes);
    }


    /* 値 */

    public WorkedTimeAsRegular workedTimeAsRegular() {
        return workedTimeAsRegular;
    }

    public WorkedTimeAsOvertime workedTimeAsOvertime() {
        return workedTimeAsOvertime;
    }

    //TODO: メソッド名が意味的におかしい
    public int storedValue() {
        return workedTimeAsRegular.minutes.plus(workedTimeAsOvertime.minutes).rawValue();
    }
}
