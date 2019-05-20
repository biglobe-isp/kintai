package com.naosim.dddwork.kintai.domain.model.timerecord.derived.detailed;

import com.naosim.dddwork.kintai.domain.core.type.time.amount.AmountOfMinutes;

import static com.naosim.dddwork.kintai.domain.model.regulation.WorkingTimeRegulations.LEGAL_WORKING_MINUTES;


/**
 * 賃金発生労働時間（残業時間）
 * <pre>
 *     残業時間は、作業開始から法定労働時間量（8時間）経過以降。
 *     休憩時間は含まない。
 *     また、日付を越えた労働時間は含めない。
 * </pre>
 */
public class WorkedTimeAsOvertime {

    final AmountOfMinutes minutes;


    /* 生成 */

    private WorkedTimeAsOvertime(ActualPaidWorkedTime actualPaidWorkedTime) {

        /* 残業時間(分) = 賃金発生労働時間量 - 法定労働時間量 */
        minutes = actualPaidWorkedTime.minus(LEGAL_WORKING_MINUTES);
    }

    public static WorkedTimeAsOvertime of(ActualPaidWorkedTime actualPaidWorkedTime) {
        return new WorkedTimeAsOvertime(actualPaidWorkedTime);
    }


    /* 値 */

    /**
     * 残業時間の「分」表現
     *
     * @return 残業時間（分）
     */
    public int minutes() {
        return minutes.rawValue();
    }

    public int storedValue() {
        return minutes();
    }
}
