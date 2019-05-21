package com.naosim.dddwork.kintai.domain.core.type.time.clock;


import com.naosim.dddwork.kintai.domain.core.type.time.amount.AmountOfMinutes;
import com.naosim.dddwork.kintai.domain.core.type.time.component.HourOfDay;
import com.naosim.dddwork.kintai.domain.core.type.time.component.MinuteOfHour;
import com.naosim.dddwork.kintai.domain.core.type.time.component.TimeOfDay;
import com.naosim.dddwork.kintai.domain.core.type.time.component.protocol.ClockTimeQuantifiable;
import com.naosim.dddwork.kintai.domain.core.type.time.component.protocol.TimeOrderComparable;
import lombok.EqualsAndHashCode;
import lombok.ToString;


/**
 * 開始時刻
 */
@EqualsAndHashCode
@ToString
public class BeginTime implements ClockTimeQuantifiable, TimeOrderComparable<BeginTime> {

    final TimeOfDay timeOfDay;


    /* 生成 */

    private BeginTime(TimeOfDay timeOfDay) {
        this.timeOfDay = timeOfDay;
    }

    private BeginTime(HourOfDay hour, MinuteOfHour minute) {
        this(TimeOfDay.of(hour, minute));
    }

    public static BeginTime of(int hour, int minute) {
        return new BeginTime(HourOfDay.of(hour), MinuteOfHour.of(minute));
    }

    public static BeginTime of(HourOfDay hour, MinuteOfHour minute) {
        return new BeginTime(hour, minute);
    }

    public static BeginTime of(String storedValue) {
        return new BeginTime(TimeOfDay.of(storedValue));
    }


    /* 値 */

    public String storedValue() {
        return timeOfDay.defaultStoredValue();
    }


    /* ClockTimeQuantifiable 準拠 */

    @Override
    public AmountOfMinutes quantityOfMinutes() {
        return timeOfDay.quantityOfMinutes();
    }


    /* TimeOrderComparable 準拠 */

    @Override
    public boolean isBefore(ClockTimeQuantifiable other) {
        return timeOfDay.isBefore(other);
    }

    @Override
    public boolean isAfter(ClockTimeQuantifiable other) {
        return timeOfDay.isAfter(other);
    }

    @Override
    public BeginTime min() {
        return new BeginTime(HourOfDay.min(), MinuteOfHour.min());
    }

    @Override
    public BeginTime max() {
        return new BeginTime(HourOfDay.max(), MinuteOfHour.max());
    }


    /* 比較 */

    public boolean isBeforeOrEqualTo(ClockTimeQuantifiable other) {
        return isBefore(other) || equals(other);
    }


    /* 時間量 */

    public AmountOfMinutes until(EndTime endTime) {
        return timeOfDay.until(endTime.timeOfDay);
    }


    /* 変換 */

    public EndTime convertToEndTime() {
        return EndTime.of(timeOfDay.hourRawValue(), timeOfDay.minuteRawValue());
    }
}
