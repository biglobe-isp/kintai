package com.naosim.dddwork.kintai.domain.model.timerecord.derived.detailed;

import com.naosim.dddwork.kintai.domain.core.type.time.amount.AmountOfMinutes;


//TODO: ここにルールコメントがあるのは違和感
/**
 * 残業時間
 * <pre>
 *     残業時間は、作業開始から8時間経過以降。
 *     休憩時間は含まない。
 *     また、日付を越えた労働時間は含めない。
 * </pre>
 */
public class WorkedTimeAsOvertime {

    final AmountOfMinutes minutes;


    /* 生成 */

    private WorkedTimeAsOvertime(AmountOfMinutes minutes) {
        this.minutes = minutes;
    }

    public static WorkedTimeAsOvertime of(int minutes) {
        return WorkedTimeAsOvertime.of(AmountOfMinutes.of(minutes));
    }

    public static WorkedTimeAsOvertime of(AmountOfMinutes minutes) {
        return new WorkedTimeAsOvertime(minutes);
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
