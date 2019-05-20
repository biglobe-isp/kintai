package com.naosim.dddwork.kintai.domain.model.timerecord;

import com.naosim.dddwork.kintai.domain.core.type.time.stamp.RecordTimestamp;
import com.naosim.dddwork.kintai.domain.model.foundation.time.range.WorkTimeRange;
import com.naosim.dddwork.kintai.domain.model.timerecord.derived.detailed.ActualUnpaidWorkedTime;
import com.naosim.dddwork.kintai.domain.model.timerecord.derived.detailed.ActualWorkedTime;
import com.naosim.dddwork.kintai.domain.model.timerecord.derived.detailed.PaidWorkedTime;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;


/**
 * 日別勤務時間
 */
@EqualsAndHashCode
@ToString
@Getter
public class DailyWorkedTime {

    /* 日別拘束時間 */
    final DailySpentTimeRangeAtWork dailySpentTimeRangeAtWork;

    /* ［導出］労働時間（賃金発生分） */
    final PaidWorkedTime paidWorkedTime;
    /* ［導出］サービス残業時間 */
    final ActualUnpaidWorkedTime actualUnpaidWorkedTime;

    /* タイムスタンプ */
    final RecordTimestamp recordTimestamp = RecordTimestamp.now();


    /* 生成 */

    private DailyWorkedTime(DailySpentTimeRangeAtWork dailySpentTimeRangeAtWork) {

        this.dailySpentTimeRangeAtWork = dailySpentTimeRangeAtWork;

        /* 拘束時間 */
        final WorkTimeRange spentTimeRange = dailySpentTimeRangeAtWork.spentTimeRange;
        /* 実際の労働時間 */
        final ActualWorkedTime actualWorkedTime = ActualWorkedTime.of(spentTimeRange);

        actualUnpaidWorkedTime = ActualUnpaidWorkedTime.of(spentTimeRange);
        paidWorkedTime = PaidWorkedTime.of(actualWorkedTime, actualUnpaidWorkedTime);
    }

    public static DailyWorkedTime of(DailySpentTimeRangeAtWork dailySpentTimeRangeAtWork) {
        return new DailyWorkedTime(dailySpentTimeRangeAtWork);
    }
}
