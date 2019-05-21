package com.naosim.dddwork.kintai.domain.core.type.time.clock;


import com.naosim.dddwork.kintai.domain.core.type.time.TimeSystem;
import com.naosim.dddwork.kintai.domain.core.type.time.amount.AmountOfMinutes;
import com.naosim.dddwork.kintai.domain.core.type.time.component.HourOfDay;
import com.naosim.dddwork.kintai.domain.core.type.time.component.MinuteOfHour;
import com.naosim.dddwork.kintai.domain.core.type.time.component.TimeOfDay;
import com.naosim.dddwork.kintai.domain.core.type.time.component.protocol.ClockTimeQuantifiable;
import com.naosim.dddwork.kintai.domain.core.type.time.component.protocol.TimeOrderComparable;
import lombok.EqualsAndHashCode;
import lombok.ToString;


/**
 * 終了時刻
 */
@EqualsAndHashCode
@ToString
public class EndTime implements ClockTimeQuantifiable, TimeOrderComparable<EndTime> {

    final TimeOfDay timeOfDay;


    /* 生成 */

    private EndTime(TimeOfDay timeOfDay) {
        this.timeOfDay = timeOfDay;
    }

    private EndTime(HourOfDay hour, MinuteOfHour minute) {
        this(TimeOfDay.of(hour, minute));
    }

    public static EndTime of(int hour, int minute) {
        return new EndTime(HourOfDay.of(hour), MinuteOfHour.of(minute));
    }

    public static EndTime of(HourOfDay hour, MinuteOfHour minute) {
        return new EndTime(hour, minute);
    }

    public static EndTime of(String storedValue) {
        return new EndTime(TimeOfDay.of(storedValue));
    }


    /* 値 */

    public int hourRawValue() {
        return timeOfDay.hourRawValue();
    }

    public int minuteRawValue() {
        return timeOfDay.minuteRawValue();
    }

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
    public EndTime min() {
        return new EndTime(HourOfDay.min(), MinuteOfHour.min());
    }

    @Override
    public EndTime max() {
        return new EndTime(HourOfDay.max(), MinuteOfHour.min());
    }


    /* 変換 */

    public EndTime convertedInto(TimeSystem timeSystem) {

        TimeOfDay converted = timeOfDay.convertedToFitDayBoundary(timeSystem.dayBoundaryHour());
        return new EndTime(converted);
    }

    public BeginTime convertToBeginTime() {
        return BeginTime.of(timeOfDay.hourRawValue(), timeOfDay.minuteRawValue());
    }
}
