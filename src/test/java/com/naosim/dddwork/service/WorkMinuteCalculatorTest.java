package com.naosim.dddwork.service;

import com.google.common.collect.ImmutableList;
import com.naosim.dddwork.domain.TimePoint;
import com.naosim.dddwork.domain.TimePointPair;
import com.naosim.dddwork.domain.WorkTimeOfDay;
import com.naosim.dddwork.domain.WorkTimeOfMonth;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalTime;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
public class WorkMinuteCalculatorTest {

    @Before
    public void setUp() {
    }

    @Test
    public void testValidateWorkTime() {
        // TODO: Not Implemented
    }

    @Test
    public void testCalculateStayTime() {
        WorkMinuteCalculator target = new WorkMinuteCalculator(null);
        LocalTime startTime = LocalTime.of(9, 40);
        LocalTime endTime = LocalTime.of(20, 20);
        int expected = (20 - 9) * 60 + (20 - 40);

        int actual = target.calculateStayTime(startTime, endTime);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testCalculateRestTimes() {
        WorkMinuteCalculator target = new WorkMinuteCalculator(null);
        LocalTime startTime = LocalTime.of(10, 0);
        LocalTime endTime = LocalTime.of(15, 30);
        List<TimePointPair> restTimes = ImmutableList.of(
                createTimePointPair(9, 10),
                createTimePointPair(12, 13),
                createTimePointPair(15, 16)
        );
        int expected = 90;

        int actual = target.calculateRestTimes(startTime, endTime, restTimes);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testCalculateRestTime1() {
        WorkMinuteCalculator target = new WorkMinuteCalculator(null);
        LocalTime startTime = LocalTime.of(10, 0);
        LocalTime endTime = LocalTime.of(15, 0);
        LocalTime restStartTime = LocalTime.of(9, 0);
        LocalTime restEndTime = LocalTime.of(10, 0);
        int expected = 0;

        int actual = target.calculateRestTime(startTime, endTime, restStartTime, restEndTime);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testCalculateRestTime2() {
        WorkMinuteCalculator target = new WorkMinuteCalculator(null);
        LocalTime startTime = LocalTime.of(9, 30);
        LocalTime endTime = LocalTime.of(15, 0);
        LocalTime restStartTime = LocalTime.of(9, 0);
        LocalTime restEndTime = LocalTime.of(10, 0);
        int expected = 30;

        int actual = target.calculateRestTime(startTime, endTime, restStartTime, restEndTime);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testCalculateRestTime3() {
        WorkMinuteCalculator target = new WorkMinuteCalculator(null);
        LocalTime startTime = LocalTime.of(9, 0);
        LocalTime endTime = LocalTime.of(15, 0);
        LocalTime restStartTime = LocalTime.of(12, 0);
        LocalTime restEndTime = LocalTime.of(13, 0);
        int expected = 60;

        int actual = target.calculateRestTime(startTime, endTime, restStartTime, restEndTime);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testCalculateRestTime4() {
        WorkMinuteCalculator target = new WorkMinuteCalculator(null);
        LocalTime startTime = LocalTime.of(9, 0);
        LocalTime endTime = LocalTime.of(14, 30);
        LocalTime restStartTime = LocalTime.of(14, 0);
        LocalTime restEndTime = LocalTime.of(15, 0);
        int expected = 30;

        int actual = target.calculateRestTime(startTime, endTime, restStartTime, restEndTime);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testCalculateRestTime5() {
        WorkMinuteCalculator target = new WorkMinuteCalculator(null);
        LocalTime startTime = LocalTime.of(9, 0);
        LocalTime endTime = LocalTime.of(14, 0);
        LocalTime restStartTime = LocalTime.of(14, 0);
        LocalTime restEndTime = LocalTime.of(15, 0);
        int expected = 0;

        int actual = target.calculateRestTime(startTime, endTime, restStartTime, restEndTime);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testCreateWorkTimeOfDay1() {
        WorkMinuteCalculator target = new WorkMinuteCalculator(null);
        int stayMinute = 9 * 60 + 30;
        int restMinute = 1 * 60;
        int standardMinute = 8 * 60;
        int workMinute = 8 * 60;
        int overWorkMinute = 30;

        WorkTimeOfDay result = target.createWorkTimeOfDay(stayMinute, restMinute, standardMinute);

        Assert.assertNotNull(result);
        Assert.assertEquals(result.getWorkMinute().getValue(), workMinute);
        Assert.assertEquals(result.getOverWorkMinute().getValue(), overWorkMinute);
    }

    @Test
    public void testCreateWorkTimeOfDay2() {
        WorkMinuteCalculator target = new WorkMinuteCalculator(null);
        int stayMinute = 9 * 60;
        int restMinute = 1 * 60;
        int standardMinute = 8 * 60;
        int workMinute = 8 * 60;
        int overWorkMinute = 0;

        WorkTimeOfDay result = target.createWorkTimeOfDay(stayMinute, restMinute, standardMinute);

        Assert.assertNotNull(result);
        Assert.assertEquals(result.getWorkMinute().getValue(), workMinute);
        Assert.assertEquals(result.getOverWorkMinute().getValue(), overWorkMinute);
    }

    @Test
    public void testCreateWorkTimeOfDay3() {
        WorkMinuteCalculator target = new WorkMinuteCalculator(null);
        int stayMinute = 8 * 60;
        int restMinute = 1 * 60;
        int standardMinute = 8 * 60;
        int workMinute = 7 * 60;
        int overWorkMinute = 0;

        WorkTimeOfDay result = target.createWorkTimeOfDay(stayMinute, restMinute, standardMinute);

        Assert.assertNotNull(result);
        Assert.assertEquals(result.getWorkMinute().getValue(), workMinute);
        Assert.assertEquals(result.getOverWorkMinute().getValue(), overWorkMinute);
    }

    @Test
    public void testCreateWorkTimeOfMonth() {
        WorkMinuteCalculator target = new WorkMinuteCalculator(null);
        int totalWorkMinuteValue = 8 * 20;
        int totalOverWorkMinuteValue = 3 * 20;

        WorkTimeOfMonth result = target.createWorkTimeOfMonth(totalWorkMinuteValue, totalOverWorkMinuteValue);

        Assert.assertNotNull(result);
        Assert.assertEquals(result.getTotalWorkMinute().getValue(), totalWorkMinuteValue);
        Assert.assertEquals(result.getTotalOverWorkMinute().getValue(), totalOverWorkMinuteValue);
    }

    private TimePointPair createTimePointPair(int startHour, int endHour) {
        return new TimePointPair(
                new TimePoint(LocalTime.of(startHour, 0)),
                new TimePoint(LocalTime.of(endHour, 0))
        );
    }
}
