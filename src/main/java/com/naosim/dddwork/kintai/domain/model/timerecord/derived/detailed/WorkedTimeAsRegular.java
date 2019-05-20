package com.naosim.dddwork.kintai.domain.model.timerecord.derived.detailed;

import com.naosim.dddwork.kintai.domain.core.type.time.amount.AmountOfMinutes;


/**
 * 勤務時間
 * <pre>
 *     休憩時間は含まない。
 *     残業時間も含む（サービス残業（日付を超えた分の労働時間）は含まない）。
 * </pre>
 */
public class WorkedTimeAsRegular {

    final AmountOfMinutes minutes;


    /* 生成 */

    private WorkedTimeAsRegular(AmountOfMinutes minutes) {
        this.minutes = minutes;
    }

    public static WorkedTimeAsRegular of(int minutes) {
        return WorkedTimeAsRegular.of(AmountOfMinutes.of(minutes));
    }

    public static WorkedTimeAsRegular of(AmountOfMinutes minutes) {
        return new WorkedTimeAsRegular(minutes);
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
