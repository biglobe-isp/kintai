package com.naosim.dddwork.domain.daily_work;

import org.junit.Assert;
import org.junit.Test;

public class Total1DayWorkTimesTest {
    @Test
    public void 勤務終了時刻が休憩開始時刻よりも前だった場合にそのまま実働時間を出力できるか() {
        WorkTimeMoments inputWorkTimes = new WorkTimeMoments(new ClockTime(9,0), new ClockTime(11,59));
        long expected = 179L;

        long actual = new Total1DayWorkTimes(inputWorkTimes).getTotal1DayWorkTimes().getTimeMomentsDifference().toMinutes();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void 勤務終了時刻が休憩時刻中だった場合に休憩開始時刻から勤務終了時刻までの休憩時間を除外できるか() {
        WorkTimeMoments inputWorkTimes = new WorkTimeMoments(new ClockTime(9,0), new ClockTime(12,1));
        long expected = 180L;

        long actual = new Total1DayWorkTimes(inputWorkTimes).getTotal1DayWorkTimes().getTimeMomentsDifference().toMinutes();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void 勤務終了時刻が休憩開始時刻よりも後だった場合に実働時間から休憩時間分だけ除外できるか() {
        WorkTimeMoments inputWorkTimes = new WorkTimeMoments(new ClockTime(9,0), new ClockTime(13,1));
        long expected = 181L;

        long actual = new Total1DayWorkTimes(inputWorkTimes).getTotal1DayWorkTimes().getTimeMomentsDifference().toMinutes();

        Assert.assertEquals(expected, actual);
    }
}