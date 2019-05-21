package com.naosim.dddwork.kintai.domain.model.timerecord.collection;

import com.google.common.collect.ImmutableMap;
import com.naosim.dddwork.kintai.domain.model.foundation.date.AttendanceDate;
import com.naosim.dddwork.kintai.domain.model.timerecord.DailyWorkedTime;
import com.naosim.dddwork.kintai.domain.model.timerecord.derived.MonthlyTotalWorkedTime;

import java.util.Map;


/**
 * 日別勤務時間のコレクション
 */
public class DailyWorkedTimeCollection {

    final ImmutableMap<AttendanceDate, DailyWorkedTime> dailyWorkedTimes;


    /* 生成 */

    public static DailyWorkedTimeCollection of(Map<AttendanceDate, DailyWorkedTime> dailyWorkedTimes) {
        return new DailyWorkedTimeCollection(dailyWorkedTimes);
    }

    public DailyWorkedTimeCollection(Map<AttendanceDate, DailyWorkedTime> dailyWorkedTimes) {
        this.dailyWorkedTimes = ImmutableMap.copyOf(dailyWorkedTimes);
    }


    /* 計算 */

    public MonthlyTotalWorkedTime total() {

        MonthlyTotalWorkedTime monthlyTotalWorkedTime = dailyWorkedTimes.values().stream().parallel().reduce(

                MonthlyTotalWorkedTime.zero(),
                (total, dailyWorkedTime) -> total.added(dailyWorkedTime.getPaidWorkedTime()),
                (leftTotal, rightTotal) -> leftTotal.added(rightTotal)
        );

        return monthlyTotalWorkedTime;
    }
}
