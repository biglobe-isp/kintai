package com.naosim.dddwork.kintai.domain.model.foundation.time.range;

import com.naosim.dddwork.kintai.domain.core.type.time.amount.AmountOfMinutes;
import com.naosim.dddwork.kintai.domain.core.type.time.clock.BeginTime;
import com.naosim.dddwork.kintai.domain.core.type.time.clock.EndTime;
import com.naosim.dddwork.kintai.domain.core.type.time.component.protocol.TimeOrderComparable;
import com.naosim.dddwork.kintai.domain.core.type.time.range.TimeRange;
import com.naosim.dddwork.kintai.domain.core.type.time.range.protocol.Range;
import com.naosim.dddwork.kintai.domain.model.foundation.time.clock.WorkBeginTime;
import com.naosim.dddwork.kintai.domain.model.foundation.time.clock.WorkEndTime;
import lombok.ToString;


/**
 * 勤務時間範囲（半開区間）
 * <pre>
 *     ●---○（end値は含まない）
 * </pre>
 */
@ToString
public class WorkTimeRange implements Range<WorkBeginTime, WorkEndTime> {

    final WorkBeginTime workBeginTime;

    final WorkEndTime workEndTime;


    /* 生成 */

    private WorkTimeRange(WorkBeginTime workBeginTime, WorkEndTime workEndTime) {

        this.workBeginTime = workBeginTime;
        this.workEndTime = workEndTime;

        if (isArrangedInReverseOrder()) {
            final String message = String.format("正しい順序の時間範囲でなければ生成できません。[workBeginTime=%s, workEndTime=%s]", workBeginTime, workEndTime);
            throw new IllegalArgumentException(message);   //TODO: Businessにする？
        }
    }

    public static WorkTimeRange of(WorkBeginTime workBeginTime, WorkEndTime workEndTime) {
        return new WorkTimeRange(workBeginTime, workEndTime);
    }


    /* Range 準拠 */

    @Override
    public WorkBeginTime beginTime() {
        return workBeginTime;
    }

    @Override
    public WorkEndTime endTime() {
        return workEndTime;
    }

    @Override
    public AmountOfMinutes amountOfMinutes() {
        return workBeginTime.until(workEndTime);
    }


    /* 順序 */

    public boolean isArrangedInOrder() {
        return workBeginTime.isBeforeOrEqualTo(workEndTime);
    }

    public boolean isArrangedInReverseOrder() {
        return !isArrangedInOrder();
    }


    /* 重なり */

    public WorkTimeRange overlappedTimeRangeWith(WorkTimeRange other) {

        final WorkBeginTime overlappedBeginTime = TimeOrderComparable.pickLater(this.workBeginTime, other.workBeginTime);
        final WorkEndTime overlappedEndTime = TimeOrderComparable.pickEarlier(this.workEndTime, other.workEndTime);

        return WorkTimeRange.of(overlappedBeginTime, overlappedEndTime);
    }

    public WorkTimeRange overlappedTimeRangeWith(TimeRange other) {

        final TimeRange me = this.convertToTimeRange();

        final BeginTime overlappedBeginTime = TimeOrderComparable.pickLater(me.beginTime(), other.beginTime());
        final EndTime overlappedEndTime = TimeOrderComparable.pickEarlier(me.endTime(), other.endTime());

        return WorkTimeRange.of(WorkBeginTime.of(overlappedBeginTime), WorkEndTime.of(overlappedEndTime));
    }


    /* 変換 */

    public TimeRange convertToTimeRange() {
        return TimeRange.of(workBeginTime.beginTime(), workEndTime.endTime());
    }
}
