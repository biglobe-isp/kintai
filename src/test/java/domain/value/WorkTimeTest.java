package domain.value;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class WorkTimeTest {

    @Test
    public void getStartTimeString() {
        assertGetStartTimeString(1, 5, "0105");
        assertGetStartTimeString(1, 10, "0110");
    }

    @Test
    public void getEndTimeString() {
        assertGetEndTimeString(9, 5, "0905");
        assertGetEndTimeString(9, 10, "0910");
        assertGetEndTimeString(10, 5, "1005");
        assertGetEndTimeString(10, 10, "1010");
    }

    @Test
    public void getWholeMinutes() {
        assertGetWholeMinutes(7, 0, 7, 0, 0);
        assertGetWholeMinutes(7, 0, 18, 45, 705);
        assertGetWholeMinutes(7, 45, 19, 0, 675);
        assertGetWholeMinutes(7, 45, 19, 55, 730);
    }

    @Test
    public void computeEachBreakMinutes() {
        BreakTime[] breakTimes = BreakTime.getBreakTimes(new WorkDate(2022, 1, 18));
        BreakTime from12To13 = breakTimes[0];
        BreakTime from18To19 = breakTimes[1];
        BreakTime from21To22 = breakTimes[2];
        assertComputeEachBreakMinutes(11, 59, from12To13, 0);
        assertComputeEachBreakMinutes(12, 0, from12To13, 0);
        assertComputeEachBreakMinutes(12, 1, from12To13, 1);
        assertComputeEachBreakMinutes(12, 59, from12To13, 59);
        assertComputeEachBreakMinutes(13, 0, from12To13, 60);
        assertComputeEachBreakMinutes(13, 1, from12To13, 60);
        assertComputeEachBreakMinutes(17, 59, from18To19, 0);
        assertComputeEachBreakMinutes(18, 0, from18To19, 0);
        assertComputeEachBreakMinutes(18, 1, from18To19, 1);
        assertComputeEachBreakMinutes(18, 59, from18To19, 59);
        assertComputeEachBreakMinutes(19, 0, from18To19, 60);
        assertComputeEachBreakMinutes(19, 1, from18To19, 60);
        assertComputeEachBreakMinutes(20, 59, from21To22, 0);
        assertComputeEachBreakMinutes(21, 0, from21To22, 0);
        assertComputeEachBreakMinutes(21, 1, from21To22, 1);
        assertComputeEachBreakMinutes(21, 59, from21To22, 59);
        assertComputeEachBreakMinutes(22, 0, from21To22, 60);
        assertComputeEachBreakMinutes(22, 1, from21To22, 60);
    }

    private void assertGetStartTimeString(int startHour, int startMinutes, String expected) {
        assertThat(new WorkTime(startHour, startMinutes, 24, 0).getStartTimeString(), is(equalTo(expected)));
    }

    private void assertGetEndTimeString(int ensHour, int ensMinutes, String expected) {
        assertThat(new WorkTime(0, 0, ensHour, ensMinutes).getEndTimeString(), is(equalTo(expected)));
    }

    private void assertGetWholeMinutes(int startHour, int startMinutes, int endHour, int endMinutes, int expected) {
        assertThat(new WorkTime(startHour, startMinutes, endHour, endMinutes).getWholeMinutes(), is(equalTo(expected)));
    }

    private void assertComputeEachBreakMinutes(int endHour, int endMinutes, BreakTime breakTime, int expected) {
        assertThat(new WorkTime(0, 0, endHour, endMinutes).computeEachBreakMinutes(breakTime), is(equalTo(expected)));
    }
}