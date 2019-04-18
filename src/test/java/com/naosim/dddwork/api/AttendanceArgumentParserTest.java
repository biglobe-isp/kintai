package com.naosim.dddwork.api;

import com.naosim.dddwork.domain.TimePoint;
import com.naosim.dddwork.domain.YearMonth;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
public class AttendanceArgumentParserTest {

    @Test
    public void testParseToLocalDateOK() {
        AttendanceArgumentParser target = new AttendanceArgumentParser();
        String value = "20190401";
        LocalDate expected = LocalDate.of(2019, 4, 1);

        LocalDate actual = target.parseToLocalDate(value);

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void testParseToLocalDateNG() {
        AttendanceArgumentParser target = new AttendanceArgumentParser();
        String value = "ABC-1234";

        try {
            target.parseToLocalDate(value);
        } catch (IllegalArgumentException e) {
            return;
        }
        Assert.fail();
    }

    @Test
    public void testParseToTimePointOK() {
        AttendanceArgumentParser target = new AttendanceArgumentParser();
        String value = "1020";
        TimePoint expected = TimePoint.of(10, 20);

        TimePoint actual = target.parseToTimePoint(value);

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void testParseToTimePointNG() {
        AttendanceArgumentParser target = new AttendanceArgumentParser();
        String value = "A-01";

        try {
            target.parseToTimePoint(value);
        } catch (IllegalArgumentException e) {
            return;
        }
        Assert.fail();
    }

    @Test
    public void testParseToYearMonthOK() {
        AttendanceArgumentParser target = new AttendanceArgumentParser();
        String value = "201904";
        YearMonth expected = YearMonth.of(2019, 4);

        YearMonth actual = target.parseToYearMonth(value);

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void testParseToYearMonthNG() {
        AttendanceArgumentParser target = new AttendanceArgumentParser();
        String value = "19XX04";

        try {
            target.parseToYearMonth(value);
        } catch (IllegalArgumentException e) {
            return;
        }
        Assert.fail();
    }
}
