package domain.value;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class WorkDateTest {

    @Test
    public void toString1() {
        assertToString(2022, 1, 1, "20220101");
        assertToString(2022, 1, 10, "20220110");
        assertToString(2022, 10, 1, "20221001");
        assertToString(2022, 10, 10, "20221010");
    }

    @Test
    public void belongs() {
        assertBelongs(2022, 1, 17, 2022, 1, true);
        assertBelongs(2022, 1, 17, 2022, 2, false);
        assertBelongs(2022, 1, 17, 2023, 1, false);
    }

    @Test
    public void isBefore() {
        assertIsBefore(2022, 1, 1, 2021, 12, 31, false);
        assertIsBefore(2022, 1, 1, 2022, 1, 1, false);
        assertIsBefore(2022, 1, 1, 2022, 1, 2, true);

        assertIsBefore(2022, 12, 31, 2022, 12, 30, false);
        assertIsBefore(2022, 12, 31, 2022, 12, 31, false);
        assertIsBefore(2022, 12, 31, 2023, 1, 1, true);
    }

    private void assertToString(int year, int month, int day, String expected) {
        assertThat(new WorkDate(year, month, day).toString(), is(equalTo(expected)));
    }

    private void assertBelongs(int dateYear, int dateMonth, int dateDay, int yearMonthYear, int yearMonthMonth, boolean expected) {
        assertThat(new WorkDate(dateYear, dateMonth, dateDay).belongs(new WorkYearMonth(yearMonthYear, yearMonthMonth)), is(expected));
    }

    private void assertIsBefore(int year1, int month1, int day1, int year2, int month2, int day2, boolean expected) {
        assertThat(new WorkDate(year1, month1, day1).isBefore(new WorkDate(year2, month2, day2)), is(expected));
    }
}