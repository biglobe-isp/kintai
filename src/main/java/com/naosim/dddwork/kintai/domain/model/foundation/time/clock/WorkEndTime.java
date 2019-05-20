package com.naosim.dddwork.kintai.domain.model.foundation.time.clock;

import com.naosim.dddwork.kintai.domain.core.type.time.amount.AmountOfMinutes;
import com.naosim.dddwork.kintai.domain.core.type.time.clock.EndTime;
import com.naosim.dddwork.kintai.domain.core.type.time.component.protocol.ClockTimeQuantifiable;
import com.naosim.dddwork.kintai.domain.core.type.time.component.protocol.TimeOrderComparable;
import com.naosim.dddwork.kintai.domain.model.regulation.WorkingTimeRegulations;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import static com.naosim.dddwork.kintai.domain.model.regulation.WorkingTimeRegulations.SPECIFIABLE_END_TIME_RANGE;


/**
 * 作業終了時刻
 * <pre>
 *     {@link com.naosim.dddwork.kintai.domain.core.type.time.TimeSystem} に基づき表現値を変換済みの終業時刻。
 *     例えば {@link com.naosim.dddwork.kintai.domain.core.type.time.TimeSystem#TIME33} の場合は下記のように変換済み。
 *     例）
 *          0:00 → 24:00
 *          1:15 → 25:15
 *          8:45 → 32:45
 *          9:00 → 33:00
 * </pre>
 */
@EqualsAndHashCode
@ToString
public class WorkEndTime implements ClockTimeQuantifiable, TimeOrderComparable<WorkEndTime> {

    final EndTime endTime;


    /* 生成 */

    private WorkEndTime(EndTime endTime) {
        this.endTime = endTime.convertedInto(WorkingTimeRegulations.APPLIED_TIME_SYSTEM);
    }

    public static WorkEndTime of(EndTime endTime) {
        return new WorkEndTime(endTime);
    }

    public static WorkEndTime of(int hour, int minute) {

        final EndTime endTime = EndTime.of(hour, minute);
        return new WorkEndTime(endTime);
    }


    /* 値 */

    public EndTime endTime() {
        return endTime;
    }

    public String storedValue() {
        return endTime.storedValue();
    }


    /* ClockTimeQuantifiable 準拠 */

    @Override
    public AmountOfMinutes quantityOfMinutes() {
        return endTime.quantityOfMinutes();
    }


    /* TimeOrderComparable 準拠 */

    @Override
    public boolean isBefore(ClockTimeQuantifiable other) {
        return endTime.isBefore(other);
    }

    @Override
    public boolean isAfter(ClockTimeQuantifiable other) {
        return endTime.isAfter(other);
    }

    @Override
    public WorkEndTime min() {
        return new WorkEndTime(SPECIFIABLE_END_TIME_RANGE.firstTime().convertToEndTime());
    }

    @Override
    public WorkEndTime max() {
        return new WorkEndTime(SPECIFIABLE_END_TIME_RANGE.lastTime());
    }
}
