package com.naosim.dddwork.service;

import com.google.common.collect.ImmutableList;
import com.naosim.dddwork.domain.TimePoint;
import com.naosim.dddwork.domain.TimeRange;
import com.naosim.dddwork.domain.WorkTimeOfDay;
import com.naosim.dddwork.domain.WorkTimeOfMonth;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

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
        TimePoint startTime = TimePoint.of(9, 40);
        TimePoint endTime = TimePoint.of(20, 20);
        int expected = (20 - 9) * 60 + (20 - 40);

        int actual = target.calculateStayTime(startTime, endTime);

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void testCalculateRestTimes() {
        WorkMinuteCalculator target = new WorkMinuteCalculator(null);
        TimePoint startTime = TimePoint.of(10, 0);
        TimePoint endTime = TimePoint.of(15, 30);
        List<TimeRange> restTimes = ImmutableList.of(
                TimeRange.of(9, 0, 10, 0),
                TimeRange.of(12, 0, 13, 0),
                TimeRange.of(15, 0, 16, 0)
        );
        int expected = 90;

        int actual = target.calculateRestTimes(startTime, endTime, restTimes);

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void testCalculateRestTime1() {
        WorkMinuteCalculator target = new WorkMinuteCalculator(null);
        TimePoint startTime = TimePoint.of(10, 0);
        TimePoint endTime = TimePoint.of(15, 0);
        TimePoint restStartTime = TimePoint.of(9, 0);
        TimePoint restEndTime = TimePoint.of(10, 0);
        int expected = 0;

        int actual = target.calculateRestTime(startTime, endTime, restStartTime, restEndTime);

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void testCalculateRestTime2() {
        WorkMinuteCalculator target = new WorkMinuteCalculator(null);
        TimePoint startTime = TimePoint.of(9, 30);
        TimePoint endTime = TimePoint.of(15, 0);
        TimePoint restStartTime = TimePoint.of(9, 0);
        TimePoint restEndTime = TimePoint.of(10, 0);
        int expected = 30;

        int actual = target.calculateRestTime(startTime, endTime, restStartTime, restEndTime);

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void testCalculateRestTime3() {
        WorkMinuteCalculator target = new WorkMinuteCalculator(null);
        TimePoint startTime = TimePoint.of(9, 0);
        TimePoint endTime = TimePoint.of(15, 0);
        TimePoint restStartTime = TimePoint.of(12, 0);
        TimePoint restEndTime = TimePoint.of(13, 0);
        int expected = 60;

        int actual = target.calculateRestTime(startTime, endTime, restStartTime, restEndTime);

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void testCalculateRestTime4() {
        WorkMinuteCalculator target = new WorkMinuteCalculator(null);
        TimePoint startTime = TimePoint.of(9, 0);
        TimePoint endTime = TimePoint.of(14, 30);
        TimePoint restStartTime = TimePoint.of(14, 0);
        TimePoint restEndTime = TimePoint.of(15, 0);
        int expected = 30;

        int actual = target.calculateRestTime(startTime, endTime, restStartTime, restEndTime);

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void testCalculateRestTime5() {
        WorkMinuteCalculator target = new WorkMinuteCalculator(null);
        TimePoint startTime = TimePoint.of(9, 0);
        TimePoint endTime = TimePoint.of(14, 0);
        TimePoint restStartTime = TimePoint.of(14, 0);
        TimePoint restEndTime = TimePoint.of(15, 0);
        int expected = 0;

        int actual = target.calculateRestTime(startTime, endTime, restStartTime, restEndTime);

        assertThat(actual).isEqualTo(expected);
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

        assertThat(result).isNotNull();
        assertThat(result.getWorkMinute().getValue()).isEqualTo(workMinute);
        assertThat(result.getOverWorkMinute().getValue()).isEqualTo(overWorkMinute);
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

        assertThat(result).isNotNull();
        assertThat(result.getWorkMinute().getValue()).isEqualTo(workMinute);
        assertThat(result.getOverWorkMinute().getValue()).isEqualTo(overWorkMinute);
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

        assertThat(result).isNotNull();
        assertThat(result.getWorkMinute().getValue()).isEqualTo(workMinute);
        assertThat(result.getOverWorkMinute().getValue()).isEqualTo(overWorkMinute);
    }

    @Test
    public void testCreateWorkTimeOfMonth() {
        WorkMinuteCalculator target = new WorkMinuteCalculator(null);
        int totalWorkMinuteValue = 8 * 20;
        int totalOverWorkMinuteValue = 3 * 20;

        WorkTimeOfMonth result = target.createWorkTimeOfMonth(totalWorkMinuteValue, totalOverWorkMinuteValue);

        assertThat(result).isNotNull();
        assertThat(result.getTotalWorkMinute().getValue()).isEqualTo(totalWorkMinuteValue);
        assertThat(result.getTotalOverWorkMinute().getValue()).isEqualTo(totalOverWorkMinuteValue);
    }
}
