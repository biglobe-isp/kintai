package com.naosim.dddwork.kintai.domain.core.type.time.component;

import com.naosim.dddwork.kintai.domain.core.type.time.amount.AmountOfMinutes;
import com.naosim.dddwork.kintai.domain.core.type.time.component.protocol.ClockTimeQuantifiable;
import com.naosim.dddwork.kintai.domain.core.type.time.component.protocol.TimeOrderComparable;
import com.naosim.dddwork.kintai.domain.model.regulation.WorkingTimeRegulations;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.text.MessageFormat;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;


/**
 * 時刻
 */
@EqualsAndHashCode
@ToString
public class TimeOfDay implements ClockTimeQuantifiable,
                                  TimeOrderComparable<TimeOfDay> {

    private final static String STORED_VALUE_FORMAT = "{0,number,00}{1,number,00}";

    private final MessageFormat _storedValueFormat = new MessageFormat(STORED_VALUE_FORMAT);

    final HourOfDay hour;
    final MinuteOfHour minute;

    /** LocalTime は正規化された値（25時制とかではない）で持つ（というかそれでしか持てない） */
    private final LocalTime asLocalTime;


    /* 生成 */

    private TimeOfDay(HourOfDay hour, MinuteOfHour minute) {

        this.hour = hour;
        this.minute = minute;

        asLocalTime = LocalTime.of(hour.normalizedValue(), minute.value);
    }

    public static TimeOfDay of(String storedValue) {

        final int hour = Integer.valueOf(storedValue.substring(0, 2));
        final int minute = Integer.valueOf(storedValue.substring(2, 4));
        return new TimeOfDay(HourOfDay.of(hour), MinuteOfHour.of(minute));
    }

    public static TimeOfDay of(int hour, int minute) {
        return new TimeOfDay(HourOfDay.of(hour), MinuteOfHour.of(minute));
    }

    public static TimeOfDay of(HourOfDay hour, MinuteOfHour minute) {
        return new TimeOfDay(hour, minute);
    }


    private static MessageFormat _storedValueFormatter() {
        return new MessageFormat(STORED_VALUE_FORMAT);
    }

    /* 値 */

    public int hourRawValue() {
        return hour.value;
    }

    public int minuteRawValue() {
        return minute.value;
    }

    public String defaultStoredValue() {
        //TODO: hourやminuteから0パディングした値を出す？
        return _storedValueFormatter().format(new Integer[] {hour.value, minute.value});
    }


    /* 時間量 */

    /**
     * この時刻から指定された時刻までの時間量（分）を算出して返す．
     * <pre>
     *     この時刻 <= 指定時刻 を前提とする。
     *     例）
     *          9:00 ~ 18:00 →  9 * 60 =  540（分）
     *          9:00 ~ 33:00 → 24 * 60 = 1440（分）
     *          9:00 ~  9:00 →  0 * 60 =    0（分）
     * </pre>
     *
     * @param time 時刻
     *
     * @return 時間量（分）
     */
    public AmountOfMinutes until(TimeOfDay time) {

        long distance = asLocalTime.until(time.asLocalTime, ChronoUnit.MINUTES);

        if (distance < 0) {
            return AmountOfMinutes.of(distance + (60 * 24));
        }

        if (distance == 0 && WorkingTimeRegulations.APPLIED_TIME_SYSTEM.isDayBoundaryConsistentWith(this.hour)) {    // 例）TimeSystem.TIME33 における 9:00 ~ 33:00 の場合にこの条件に一致する
            return AmountOfMinutes.of(60 * 24);
        }

        return AmountOfMinutes.of(distance);
    }


    /* ClockTimeQuantifiable 準拠 */

    @Override
    public AmountOfMinutes quantityOfMinutes() {
        return hour.quantityOfMinutes().plus(minute.quantityOfMinutes());
    }


    /* TimeOrderComparable 準拠 */

    @Override
    public boolean isBefore(ClockTimeQuantifiable other) {

        final AmountOfMinutes thisAmount = quantityOfMinutes();
        final AmountOfMinutes otherAmount = other.quantityOfMinutes();

        return thisAmount.isLessThan(otherAmount);
    }

    @Override
    public boolean isAfter(ClockTimeQuantifiable other) {

        final AmountOfMinutes thisAmount = quantityOfMinutes();
        final AmountOfMinutes otherAmount = other.quantityOfMinutes();

        return thisAmount.isGreaterThan(otherAmount);
    }

    @Override
    public TimeOfDay min() {
        return TimeOfDay.of(HourOfDay.min(), MinuteOfHour.min());
    }

    @Override
    public TimeOfDay max() {
        return TimeOfDay.of(HourOfDay.max(), MinuteOfHour.min());
    }


    /* 変換 */

    public TimeOfDay convertedToFitDayBoundary(HourOfDay dayBoundaryHour) {

        final int boundaryHourValue = dayBoundaryHour.value;
        if ((hour.value < boundaryHourValue)
         || (hour.value == boundaryHourValue && minute.value == MinuteOfHour.MIN)) {

            return TimeOfDay.of(hour.convertedToFitDayBoundary(dayBoundaryHour), minute.copy());
        }

        return this;
    }
}
