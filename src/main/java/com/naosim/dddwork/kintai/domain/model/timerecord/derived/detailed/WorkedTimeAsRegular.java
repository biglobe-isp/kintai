package com.naosim.dddwork.kintai.domain.model.timerecord.derived.detailed;

import com.naosim.dddwork.kintai.domain.core.type.time.amount.AmountOfMinutes;

import static com.naosim.dddwork.kintai.domain.model.regulation.WorkingTimeRegulations.LEGAL_WORKING_MINUTES;


/**
 * 賃金発生労働時間（通常時間）
 * <pre>
 *     法定労働時間量内の労働時間。
 *     休憩時間は含まない。
 * </pre>
 */
public class WorkedTimeAsRegular {

    final AmountOfMinutes minutes;


    /* 生成 */

    private WorkedTimeAsRegular(ActualPaidWorkedTime actualPaidWorkedTime) {

        /* 通常労働時間(分) = min(賃金が発生する実際の労働時間量, 法定労働時間) */
        minutes = AmountOfMinutes.findMinimum(actualPaidWorkedTime.actualPaidWorkMinutes, LEGAL_WORKING_MINUTES);
    }

    public static WorkedTimeAsRegular of(ActualPaidWorkedTime actualPaidWorkedTime) {
        return new WorkedTimeAsRegular(actualPaidWorkedTime);
    }


    /* 値 */

    /**
     * 勤務時間の「分」表現
     *
     * @return 勤務時間（分）
     */
    public int minutes() {
        return minutes.rawValue();
    }

    public int storedValue() {
        return minutes();
    }
}
