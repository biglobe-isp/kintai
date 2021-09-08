package com.naosim.dddwork.domain;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class TimeSpanTest {
    @Before
    public void setup() {
    }

    public static class _test {
        @Test
        public void _0900_1800() {
            TimeSpan time = new TimeSpan("0900", "1800");
            assertEquals(540, time.getMinutes());
        }

        @Test
        public void _0930_1800() {
            TimeSpan time = new TimeSpan("0930", "1800");
            assertEquals(510, time.getMinutes());
        }

        @Test
        public void _0900_1730() {
            TimeSpan time = new TimeSpan("0930", "1800");
            assertEquals(510, time.getMinutes());
        }

        @Test
        public void _0930_1830() {
            TimeSpan time = new TimeSpan("0930", "1830");
            assertEquals(540, time.getMinutes());
        }
    }
}