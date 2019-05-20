package com.naosim.dddwork.kintai.domain.model.regulation;

import com.naosim.dddwork.kintai.domain.core.type.time.amount.AmountOfMinutes;
import com.naosim.dddwork.kintai.domain.core.type.time.clock.BeginTime;
import com.naosim.dddwork.kintai.domain.core.type.time.clock.EndTime;
import com.naosim.dddwork.kintai.domain.core.type.time.range.TimeRange;
import com.naosim.dddwork.kintai.domain.model.foundation.time.range.WorkTimeRange;

import java.util.Arrays;


/**
 * 休憩時間規定
 */
public enum RestTerm {

    /** 昼休み */
    NOON(BeginTime.of(12, 0), EndTime.of(13, 0)),
    /** 夕休み */
    SUNSET(BeginTime.of(18, 0), EndTime.of(19, 0)),
    /** 夜休み */
    NIGHT(BeginTime.of(21, 0), EndTime.of(22, 0)),
    ;

    private final TimeRange _restTimeRange;

    RestTerm(BeginTime beginTime, EndTime endTime) {
        _restTimeRange = TimeRange.of(beginTime, endTime);
    }


    /* 計算ルール */

    /**
     * 指定された拘束時間のうち、この休憩時間帯にあたる部分の休憩時間を算出して返す．
     *
     * @param spentTimeRange 拘束時間
     *
     * @return この休憩時間帯にあたる部分の休憩時間
     */
    AmountOfMinutes actualRestedMinutesIn(TimeRange spentTimeRange) {

        final TimeRange overlappedRange = spentTimeRange.overlappedTimeRangeWith(_restTimeRange);

        if (overlappedRange.isArrangedInReverseOrder()) {
            return AmountOfMinutes.zero();
        }

        return overlappedRange.amountOfMinutes();
    }

    AmountOfMinutes actualRestedMinutesIn(WorkTimeRange spentTimeRange) {

        final WorkTimeRange overlappedRange = spentTimeRange.overlappedTimeRangeWith(_restTimeRange);
        return overlappedRange.amountOfMinutes();
    }



    /**
     * 指定された拘束時間のうち、全ての休憩時間帯にあたる部分の休憩時間を算出して返す．
     *
     * @param spentTimeRange 拘束時間
     *
     * @return 全ての休憩時間帯にあたる部分の休憩時間
     */
    static AmountOfMinutes actualTotalRestMinutesIn(TimeRange spentTimeRange) {

        return Arrays.stream(values()).parallel().reduce(

                AmountOfMinutes.zero(),
                (minutes, restPeriod) -> minutes.plus(restPeriod.actualRestedMinutesIn(spentTimeRange)),
                (leftMinutes, rightMinutes) -> leftMinutes.plus(rightMinutes)
        );
    }

    /**
     * 指定された拘束時間のうち、全ての休憩時間帯にあたる部分の休憩時間を算出して返す．
     * <pre>
     *     {@link WorkTimeRange} は幅がより限定されているので計算に使うには {@link TimeRange} に変換してやる必要がある？
     *     {@link WorkTimeRange} は幅がより限定されているので計算に使うには {@link TimeRange} に変換してやる必要がある？
     * </pre>
     *
     * @param spentTimeRange 拘束時間
     *
     * @return 全ての休憩時間帯にあたる部分の休憩時間
     */
    static AmountOfMinutes actualTotalRestMinutesIn(WorkTimeRange spentTimeRange) {

        TimeRange converted = spentTimeRange.convertToTimeRange();
        final AmountOfMinutes result = actualTotalRestMinutesIn(converted);
        return result;
    }
}
