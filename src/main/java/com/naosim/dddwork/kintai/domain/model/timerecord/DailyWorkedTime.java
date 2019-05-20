package com.naosim.dddwork.kintai.domain.model.timerecord;

import com.naosim.dddwork.kintai.domain.core.type.time.amount.AmountOfMinutes;
import com.naosim.dddwork.kintai.domain.core.type.time.stamp.RecordTimestamp;
import com.naosim.dddwork.kintai.domain.model.foundation.time.range.WorkTimeRange;
import com.naosim.dddwork.kintai.domain.model.regulation.WorkTimeCalculation;
import com.naosim.dddwork.kintai.domain.model.timerecord.derived.detailed.PaidWorkedTime;
import com.naosim.dddwork.kintai.domain.model.timerecord.derived.detailed.UnpaidWorkedTime;
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

    final DailySpentTimeRangeAtWork dailySpentTimeRangeAtWork;
    final PaidWorkedTime paidWorkedTime;
    final UnpaidWorkedTime unpaidWorkedTime;
    final RecordTimestamp recordTimestamp = RecordTimestamp.now();


    /* 生成 */

    private DailyWorkedTime(DailySpentTimeRangeAtWork dailySpentTimeRangeAtWork) {

        this.dailySpentTimeRangeAtWork = dailySpentTimeRangeAtWork;

        /* 拘束時間 */
        final WorkTimeRange spentTimeRange = dailySpentTimeRangeAtWork.spentTimeRange;

        /* サービス残業時間（分） */
        final AmountOfMinutes actualUnpaidOvertimeMinutes = WorkTimeCalculation.actualTotalUnpaidOvertimeIn(spentTimeRange);
        unpaidWorkedTime = UnpaidWorkedTime.of(actualUnpaidOvertimeMinutes);

        /* 実際の労働時間（分） */
        final AmountOfMinutes actualWorkedMinutes = WorkTimeCalculation.actualTotalWorkMinutesIn(spentTimeRange);
        /* 賃金発生労働時間（分） = 実際の労働時間 - サービス残業時間 */
        final AmountOfMinutes actualPaidWorkMinutes = actualWorkedMinutes.minus(actualUnpaidOvertimeMinutes);
        paidWorkedTime = PaidWorkedTime.of(actualPaidWorkMinutes);
    }

    public static DailyWorkedTime of(DailySpentTimeRangeAtWork dailySpentTimeRangeAtWork) {
        return new DailyWorkedTime(dailySpentTimeRangeAtWork);
    }
}
