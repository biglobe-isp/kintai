package com.naosim.dddwork.domain.daily_work;

import org.junit.Assert;
import org.junit.Test;

public class ScheduledWorkingMinutesTest {
    @Test
    public void 所定労働時間が休憩時間を除外した時間になっているか() {
        long expected = 480L;

        ScheduledWorkingMinutes actual = new ScheduledWorkingMinutes();

        Assert.assertEquals(expected, actual.getScheduledWorkTimes().toMinutes());
    }
}