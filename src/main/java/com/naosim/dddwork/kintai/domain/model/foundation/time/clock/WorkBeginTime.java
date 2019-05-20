package com.naosim.dddwork.kintai.domain.model.foundation.time.clock;

import com.naosim.dddwork.kintai.domain.core.type.time.amount.AmountOfMinutes;
import com.naosim.dddwork.kintai.domain.core.type.time.clock.BeginTime;
import com.naosim.dddwork.kintai.domain.core.type.time.component.protocol.ClockTimeQuantifiable;
import com.naosim.dddwork.kintai.domain.core.type.time.component.protocol.TimeOrderComparable;
import com.naosim.dddwork.kintai.domain.model.regulation.WorkTimeValidation;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import static com.naosim.dddwork.kintai.domain.model.regulation.WorkingTimeRegulations.SPECIFIABLE_BEGIN_TIME_RANGE;
import static com.naosim.dddwork.kintai.domain.model.regulation.WorkingTimeRegulations.SPECIFIABLE_END_TIME_RANGE;


/**
 * 作業開始時刻
 * <pre>
 *     {@link WorkTimeValidation} で検証済みの始業時刻。
 * </pre>
 */
@EqualsAndHashCode
@ToString
public class WorkBeginTime implements ClockTimeQuantifiable, TimeOrderComparable<WorkBeginTime> {

    final BeginTime beginTime;


    /* 生成 */

    private WorkBeginTime(BeginTime beginTime) {

        WorkTimeValidation.validate(beginTime);
        this.beginTime = beginTime;
    }

    public static WorkBeginTime of(BeginTime beginTime) {
        return new WorkBeginTime(beginTime);
    }

    public static WorkBeginTime of(int hour, int minute) {

        final BeginTime beginTime = BeginTime.of(hour, minute);
        return new WorkBeginTime(beginTime);
    }


    /* 値 */

    public BeginTime beginTime() {
        return beginTime;
    }

    public String storedValue() {
        return beginTime.storedValue();
    }


    /* ClockTimeQuantifiable 準拠 */

    @Override
    public AmountOfMinutes quantityOfMinutes() {
        return beginTime.quantityOfMinutes();
    }


    /* TimeOrderComparable 準拠 */

    @Override
    public boolean isBefore(ClockTimeQuantifiable other) {
        return beginTime.isBefore(other);
    }

    @Override
    public boolean isAfter(ClockTimeQuantifiable other) {
        return beginTime.isAfter(other);
    }

    @Override
    public WorkBeginTime min() {
        return new WorkBeginTime(SPECIFIABLE_BEGIN_TIME_RANGE.firstTime());
    }

    @Override
    public WorkBeginTime max() {
        return new WorkBeginTime(SPECIFIABLE_END_TIME_RANGE.lastTime().convertToBeginTime());
    }


    /* 比較 */

    public boolean isBeforeOrEqualTo(ClockTimeQuantifiable other) {
        return isBefore(other) || equals(other);
    }


    /* 時間量 */

    public AmountOfMinutes until(WorkEndTime workEndTime) {
        return beginTime.until(workEndTime.endTime);
    }
}
