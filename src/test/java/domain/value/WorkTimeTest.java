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
    public void computeWorkMinutes() {
        assertComputeWorkMinutes(0, 0, 18, 0, new WorkMinutes(1020));
        assertComputeWorkMinutes(0, 1, 18, 0, new WorkMinutes(1019));
        assertComputeWorkMinutes(8, 59, 18, 0, new WorkMinutes(481));
        assertComputeWorkMinutes(9, 0, 18, 0, new WorkMinutes(480));

        assertComputeWorkMinutes(9, 0, 11, 59, new WorkMinutes(179));
        assertComputeWorkMinutes(9, 0, 12, 0, new WorkMinutes(180));
        assertComputeWorkMinutes(9, 0, 12, 1, new WorkMinutes(180));
        assertComputeWorkMinutes(9, 0, 12, 59, new WorkMinutes(180));
        assertComputeWorkMinutes(9, 0, 13, 0, new WorkMinutes(180));
        assertComputeWorkMinutes(9, 0, 13, 1, new WorkMinutes(181));
        assertComputeWorkMinutes(9, 0, 17, 59, new WorkMinutes(479));
        assertComputeWorkMinutes(9, 0, 18, 0, new WorkMinutes(480));
        assertComputeWorkMinutes(9, 0, 18, 1, new WorkMinutes(480));
        assertComputeWorkMinutes(9, 0, 18, 59, new WorkMinutes(480));
        assertComputeWorkMinutes(9, 0, 19, 0, new WorkMinutes(480));
        assertComputeWorkMinutes(9, 0, 19, 1, new WorkMinutes(481));
        assertComputeWorkMinutes(9, 0, 20, 59, new WorkMinutes(599));
        assertComputeWorkMinutes(9, 0, 21, 0, new WorkMinutes(600));
        assertComputeWorkMinutes(9, 0, 21, 1, new WorkMinutes(600));
        assertComputeWorkMinutes(9, 0, 21, 59, new WorkMinutes(600));
        assertComputeWorkMinutes(9, 0, 22, 0, new WorkMinutes(600));
        assertComputeWorkMinutes(9, 0, 22, 1, new WorkMinutes(601));
        assertComputeWorkMinutes(9, 0, 23, 59, new WorkMinutes(719));
        assertComputeWorkMinutes(9, 0, 24, 0, new WorkMinutes(720));
    }

    @Test
    public void computeOverWorkMinutes() {
        assertComputeOverWorkMinutes(0, 0, 18, 0, new OverWorkMinutes(540));
        assertComputeOverWorkMinutes(0, 1, 18, 0, new OverWorkMinutes(539));
        assertComputeOverWorkMinutes(8, 59, 18, 0, new OverWorkMinutes(1));
        assertComputeOverWorkMinutes(9, 0, 18, 0, new OverWorkMinutes(0));

        assertComputeOverWorkMinutes(9, 0, 11, 59, new OverWorkMinutes(0));
        assertComputeOverWorkMinutes(9, 0, 12, 0, new OverWorkMinutes(0));
        assertComputeOverWorkMinutes(9, 0, 12, 1, new OverWorkMinutes(0));
        assertComputeOverWorkMinutes(9, 0, 12, 59, new OverWorkMinutes(0));
        assertComputeOverWorkMinutes(9, 0, 13, 0, new OverWorkMinutes(0));
        assertComputeOverWorkMinutes(9, 0, 13, 1, new OverWorkMinutes(0));
        assertComputeOverWorkMinutes(9, 0, 17, 59, new OverWorkMinutes(0));
        assertComputeOverWorkMinutes(9, 0, 18, 0, new OverWorkMinutes(0));
        assertComputeOverWorkMinutes(9, 0, 18, 1, new OverWorkMinutes(0));
        assertComputeOverWorkMinutes(9, 0, 18, 59, new OverWorkMinutes(0));
        assertComputeOverWorkMinutes(9, 0, 19, 0, new OverWorkMinutes(0));
        assertComputeOverWorkMinutes(9, 0, 19, 1, new OverWorkMinutes(1));
        assertComputeOverWorkMinutes(9, 0, 20, 59, new OverWorkMinutes(119));
        assertComputeOverWorkMinutes(9, 0, 21, 0, new OverWorkMinutes(120));
        assertComputeOverWorkMinutes(9, 0, 21, 1, new OverWorkMinutes(120));
        assertComputeOverWorkMinutes(9, 0, 21, 59, new OverWorkMinutes(120));
        assertComputeOverWorkMinutes(9, 0, 22, 0, new OverWorkMinutes(120));
        assertComputeOverWorkMinutes(9, 0, 22, 1, new OverWorkMinutes(121));
        assertComputeOverWorkMinutes(9, 0, 23, 59, new OverWorkMinutes(239));
        assertComputeOverWorkMinutes(9, 0, 24, 0, new OverWorkMinutes(240));
    }

    private void assertGetStartTimeString(int startHour, int startMinutes, String expected) {
        assertThat(new WorkTime(startHour, startMinutes, 24, 0).getStartTimeString(), is(equalTo(expected)));
    }

    private void assertGetEndTimeString(int ensHour, int ensMinutes, String expected) {
        assertThat(new WorkTime(0, 0, ensHour, ensMinutes).getEndTimeString(), is(equalTo(expected)));
    }

    private void assertComputeWorkMinutes(int startHour, int startMinutes, int endHour, int endMinutes, WorkMinutes expected) {
        assertThat(new WorkTime(startHour, startMinutes, endHour, endMinutes).computeWorkMinutes(), is(equalTo(expected)));
    }

    private void assertComputeOverWorkMinutes(int startHour, int startMinutes, int endHour, int endMinutes, OverWorkMinutes expected) {
        assertThat(new WorkTime(startHour, startMinutes, endHour, endMinutes).computeOverWorkMinutes(), is(equalTo(expected)));
    }
}