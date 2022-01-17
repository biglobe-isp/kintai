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

    private void assertToString(int year, int month, int day, String expected) {
        assertThat(new WorkDate(year, month, day).toString(), is(equalTo(expected)));
    }

    private void assertBelongs(int dateYear, int dateMonth, int dateDay, int yearMonthYear, int yearMonthMonth, boolean expected) {
        assertThat(new WorkDate(dateYear, dateMonth, dateDay).belongs(new WorkYearMonth(yearMonthYear, yearMonthMonth)), is(expected));
    }
}