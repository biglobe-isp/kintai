package com.naosim.dddwork.kintai.domain.core.type.time.component;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;


/**
 * 時刻
 */
@EqualsAndHashCode
@ToString
@Getter
//TODO: Getterは暫定的に使用中
public class TimeOfDay {

    final HourOfDay hour;
    final MinuteOfHour minute;


    public static TimeOfDay of(int hour, int minute) {
        return new TimeOfDay(HourOfDay.of(hour), MinuteOfHour.of(minute));
    }

    public static TimeOfDay of(HourOfDay hour, MinuteOfHour minute) {
        return new TimeOfDay(hour, minute);
    }

    public TimeOfDay(HourOfDay hour, MinuteOfHour minute) {

        this.hour = hour;
        this.minute = minute;
    }


    public int hourRawValue() {
        return hour.value;
    }

    public int minuteRawValue() {
        return minute.value;
    }

    public String defaultStoredValue() {

        MessageFormat mf = new MessageFormat("{0,number,00}{1,number,00}");
        return mf.format(new Integer[] {hour.value, minute.value});
    }


//    int comparableAmount() {
//        return hour.comparableAmount() + minute.comparableAmount();
//    }
//
//    public boolean isBefore(TimeOfDay other) {
//
//        final int thisAmount = comparableAmount();
//        final int otherAmount = other.comparableAmount();
//
//        return thisAmount < otherAmount;
////        return asLocalTime.isBefore(time.asLocalTime);
//    }
//
//    public boolean isAfter(TimeOfDay other) {
//
//        final int thisAmount = comparableAmount();
//        final int otherAmount = other.comparableAmount();
//
//        return thisAmount > otherAmount;
////        return asLocalTime.isAfter(time.asLocalTime);
//    }
//
//    public long until(TimeOfDay time, ChronoUnit unit) {
//        return asLocalTime.until(time.asLocalTime, unit);
//    }
//
//
//
//    public static TimeOfDay findMinimum(TimeOfDay... evaluationTargets) {
//
//        return Arrays.stream(evaluationTargets).reduce(
//                TimeOfDay.ofMaximumInDay(),
//                (minCandidate, target) -> (target.isBefore(minCandidate)) ? target : minCandidate
//        );
//
////        TimeOfDay minCandidate = TimeOfDay.findMaximum();
////
////        for (TimeOfDay timeOfDay: evaluationTargets) {
////
////            if (timeOfDay.isBefore(minCandidate)) {
////                minCandidate = timeOfDay;
////            }
////        }
////
////        return minCandidate;
//    }
//
//    public static TimeOfDay findMaximum(TimeOfDay... evaluationTargets) {
//
//        return Arrays.stream(evaluationTargets).reduce(
//                TimeOfDay.ofMinimumInDay(),
//                (maxCandidate, target) -> (target.isAfter(maxCandidate)) ? target : maxCandidate
//        );
//
////        TimeOfDay minCandidate = TimeOfDay.findMininum();
////
////        for (TimeOfDay timeOfDay: evaluationTargets) {
////
////            if (timeOfDay.isAfter(minCandidate)) {
////                minCandidate = timeOfDay;
////            }
////        }
////
////        return minCandidate;
//    }
}
