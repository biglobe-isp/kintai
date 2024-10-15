package com.naosim.dddwork.domain.daily_work;

import org.junit.Assert;
import org.junit.Test;

import java.time.LocalTime;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.fail;

public class CalculateWorkingMinutesDomainServiceTest {
    @Test
    public void 勤務開始時間が勤務終了時間より後の場合に正しいエラーが出るか() {
        StartWorkTime inputStartTime = new StartWorkTime(LocalTime.of(9,0));
        EndWorkTime inputEndTime = new EndWorkTime(LocalTime.of(8,59));
        String expectedMessage = "勤務終了時刻が勤務開始時刻よりも前に設定されています";

        try {
            CalculateWorkingMinutesDomainService.calcWorkTimes(inputStartTime, inputEndTime);

            fail("バリデーションが機能していない");
        }
        catch (Exception e) {
            assertThat(e.getMessage(), is(expectedMessage));
        }
    }

    @Test
    public void 勤務終了時刻が休憩開始時刻よりも前だった場合にそのまま実働時間を出力できるか() {
        StartWorkTime inputStartTime = new StartWorkTime(LocalTime.of(9,0));
        EndWorkTime inputEndTime = new EndWorkTime(LocalTime.of(11,59));
        long expected = 179L;

        long actual = new DailyWorkingHours(inputStartTime, inputEndTime).getValue().toMinutes();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void 勤務終了時刻が休憩時刻中だった場合に休憩開始時刻から勤務終了時刻までの休憩時間を除外できるか() {
        StartWorkTime inputStartTime = new StartWorkTime(LocalTime.of(9,0));
        EndWorkTime inputEndTime = new EndWorkTime(LocalTime.of(12,1));
        long expected = 180L;

        long actual = new DailyWorkingHours(inputStartTime, inputEndTime).getValue().toMinutes();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void 勤務終了時刻が休憩開始時刻よりも後だった場合に実働時間から休憩時間分だけ除外できるか() {
        StartWorkTime inputStartTime = new StartWorkTime(LocalTime.of(9,0));
        EndWorkTime inputEndTime = new EndWorkTime(LocalTime.of(13,1));
        long expected = 181L;

        long actual = new DailyWorkingHours(inputStartTime, inputEndTime).getValue().toMinutes();

        Assert.assertEquals(expected, actual);
    }
}