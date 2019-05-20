package com.naosim.dddwork.kintai.domain.model.regulation;

import com.naosim.dddwork.kintai.domain.core.type.time.amount.AmountOfMinutes;
import com.naosim.dddwork.kintai.domain.core.type.time.range.TimeRange;
import com.naosim.dddwork.kintai.domain.model.foundation.time.range.WorkTimeRange;

import static com.naosim.dddwork.kintai.domain.model.regulation.WorkingTimeRegulations.UNPAID_OVERTIME_HOURS;


/**
 * 勤務時間計算
 * <pre>
 *     計算はこのクラスからしか公開していない。
 *     {@link RestTerm} にも計算はあるが、そちらはパッケージプライベートにしている。
 * </pre>
 */
//THINK: TimeRange と WorkTimeRange を同一視できるか？
public final class WorkTimeCalculation {


    /* 計算ルール */

    /**
     * 指定された拘束時間のうち休憩時間を除外した実質労働時間を算出して返す．
     * <pre>
     *  サービス残業の考慮はしない。
     *  あくまでも指定された拘束時間から、{@link RestTerm 休憩時間規定} に定義されている休憩時間を除外した時間量を求める。
     * </pre>
     *
     * @param spentTimeRange サービス残業時間を除外した拘束時間
     *
     * @return 実質労働時間
     */
    public static AmountOfMinutes actualTotalWorkMinutesIn(TimeRange spentTimeRange) {

        final AmountOfMinutes spentMinutes = spentTimeRange.amountOfMinutes();
        final AmountOfMinutes totalRestedMinutes = RestTerm.actualTotalRestMinutesIn(spentTimeRange);

        return spentMinutes.minus(totalRestedMinutes);
    }

    /**
     * 指定された拘束時間のうち休憩時間を除外した実質労働時間を算出して返す．
     * <pre>
     *  サービス残業の考慮はしない。
     *  あくまでも指定された拘束時間から、{@link RestTerm 休憩時間規定} に定義されている休憩時間を除外した時間量を求める。
     * </pre>
     *
     * @param spentTimeRange サービス残業時間を除外した拘束時間
     *
     * @return 実質労働時間
     */
    public static AmountOfMinutes actualTotalWorkMinutesIn(WorkTimeRange spentTimeRange) {

        final AmountOfMinutes spentMinutes = spentTimeRange.amountOfMinutes();
        final AmountOfMinutes totalRestedMinutes = RestTerm.actualTotalRestMinutesIn(spentTimeRange);

        return spentMinutes.minus(totalRestedMinutes);
    }

    /**
     * 指定された拘束時間のうちサービス残業にあたる時間量を算出して返す．
     *
     * @param spentTimeRange 拘束時間
     *
     * @return サービス残業時間
     */
    public static AmountOfMinutes actualTotalUnpaidOvertimeIn(WorkTimeRange spentTimeRange) {

        //NOTE: WorkTimeRangeのまま計算すると結果が許容されない範囲になることがある（WorkTimeRangeは制限が入っているので）
        final TimeRange convertedSpentTimeRange = spentTimeRange.convertToTimeRange();

        final TimeRange unpaidOvertimeRange = convertedSpentTimeRange.overlappedTimeRangeWith(UNPAID_OVERTIME_HOURS);
        if (unpaidOvertimeRange.isArrangedInReverseOrder()) {
            return AmountOfMinutes.zero();
        }

        return unpaidOvertimeRange.amountOfMinutes();
    }
}
