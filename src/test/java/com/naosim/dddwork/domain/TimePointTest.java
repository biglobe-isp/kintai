package com.naosim.dddwork.domain;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
public class TimePointTest {

    @Test
    public void testToMinuteValue() {
        TimePoint target = new TimePoint(5, 25);  // 325
        int expected = 325;

        int actual = target.toMinuteValue();

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void testIsBefore1() {
        TimePoint target = new TimePoint(5, 25);  // 325
        TimePoint other = new TimePoint(10, 50);  // 650

        boolean actual = target.isBefore(other);

        assertThat(actual).isTrue();
    }

    @Test
    public void testIsBefore2() {
        TimePoint target = new TimePoint(10, 50); // 650
        TimePoint other = new TimePoint(5, 25);   // 325

        boolean actual = target.isBefore(other);

        assertThat(actual).isFalse();
    }

    @Test
    public void testIsBefore3() {
        TimePoint target = new TimePoint(5, 25);  // 325
        TimePoint other = new TimePoint(5, 25);   // 325

        boolean actual = target.isBefore(other);

        assertThat(actual).isFalse();
    }

    @Test
    public void testIsAfter1() {
        TimePoint target = new TimePoint(5, 25);  // 325
        TimePoint other = new TimePoint(10, 50);  // 650

        boolean actual = target.isAfter(other);

        assertThat(actual).isFalse();
    }

    @Test
    public void testIsAfter2() {
        TimePoint target = new TimePoint(10, 50); // 650
        TimePoint other = new TimePoint(5, 25);   // 325

        boolean actual = target.isAfter(other);

        assertThat(actual).isTrue();
    }

    @Test
    public void testIsAfter3() {
        TimePoint target = new TimePoint(5, 25);  // 325
        TimePoint other = new TimePoint(5, 25);   // 325

        boolean actual = target.isAfter(other);

        assertThat(actual).isFalse();
    }

    @Test
    public void testToString() {
        TimePoint target = new TimePoint(5, 25);
        String expected = "05:25";

        String actual = target.toString();

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void testOf() {
        TimePoint expected = new TimePoint(5, 25);

        TimePoint actual = TimePoint.of(5, 25);

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void testParse() {
        String value = "05:25";
        TimePoint expected = new TimePoint(5, 25);

        TimePoint actual = TimePoint.parse(value);

        assertThat(actual).isEqualTo(expected);
    }
}
