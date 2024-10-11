package com.naosim.dddwork.domain.daily_work;

import org.junit.Assert;
import org.junit.Test;

public class ScheduledWorkTimesTest {
    @Test
    public void 所定労働時間が休憩時間を除外した時間になっているか() {
        long expected = 480L;

        ScheduledWorkTimes actual = new ScheduledWorkTimes();

        Assert.assertEquals(expected, actual.getScheduledWorkTimes().toMinutes());
    }
}