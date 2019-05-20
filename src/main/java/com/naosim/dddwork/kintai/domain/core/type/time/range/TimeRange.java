package com.naosim.dddwork.kintai.domain.core.type.time.range;

import com.naosim.dddwork.kintai.domain.core.type.time.amount.AmountOfMinutes;
import com.naosim.dddwork.kintai.domain.core.type.time.clock.BeginTime;
import com.naosim.dddwork.kintai.domain.core.type.time.clock.EndTime;
import com.naosim.dddwork.kintai.domain.core.type.time.component.protocol.TimeOrderComparable;
import com.naosim.dddwork.kintai.domain.core.type.time.range.protocol.Range;
import lombok.ToString;


/**
 * 半開区間の時間範囲
 * <pre>
 *     TimeRange: ●---○ （end値は含まない） ← こちら！
 *     ClosedTimeRange: ●---● （end値を含む）
 *
 *     参考
 *     https://www.wikiwand.com/ja/%E5%8C%BA%E9%96%93_(%E6%95%B0%E5%AD%A6)#/.E7.94.A8.E8.AA.9E
 * </pre>
 */
@ToString
public class TimeRange implements Range<BeginTime, EndTime> {

    final BeginTime beginTime;
    final EndTime endTime;


    /* 生成 */

    private TimeRange(BeginTime beginTime, EndTime endTime) {

        this.beginTime = beginTime;
        this.endTime = endTime;
    }

    public static TimeRange of(BeginTime beginTime, EndTime endTime) {
        return new TimeRange(beginTime, endTime);
    }



    /* Range 準拠 */

    @Override
    public BeginTime beginTime() {
        return beginTime;
    }

    @Override
    public EndTime endTime() {
        return endTime;
    }

    @Override
    public AmountOfMinutes amountOfMinutes() {
        return beginTime.until(endTime);
    }


    /* 順序 */

    public boolean isArrangedInOrder() {
        return beginTime.isBeforeOrEqualTo(endTime);
    }

    public boolean isArrangedInReverseOrder() {
        return !isArrangedInOrder();
    }


    /* 重なり */

    public TimeRange overlappedTimeRangeWith(TimeRange other) {

        final BeginTime overlappedBeginTime = TimeOrderComparable.pickLater(this.beginTime, other.beginTime);
        final EndTime overlappedEndTime = TimeOrderComparable.pickEarlier(this.endTime, other.endTime);

        return TimeRange.of(overlappedBeginTime, overlappedEndTime);
    }

    public AmountOfMinutes overlappedMinutes(TimeRange other) {

        final TimeRange overlappedTimeRange = overlappedTimeRangeWith(other);
        return overlappedTimeRange.amountOfMinutes();
    }
}
