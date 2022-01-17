package domain.entity;

import domain.value.WorkDate;
import domain.value.WorkTime;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class WorkRecordTest {
    private WorkDate WORK_DATA = new WorkDate(2022, 1, 13);

    @Test
    public void getWorkDateString() {
        assertGetWorkDateString(2022, 1, 1, "20220101");
        assertGetWorkDateString(2022, 1, 10, "20220110");
        assertGetWorkDateString(2022, 10, 1, "20221001");
        assertGetWorkDateString(2022, 10, 10, "20221010");
    }

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
    public void computeComputeWorkMinutes() {
        assertComputeWorkMinutes(0, 0, 18, 0, 1020);
        assertComputeWorkMinutes(0, 1, 18, 0, 1019);
        assertComputeWorkMinutes(8, 59, 18, 0, 481);
        assertComputeWorkMinutes(9, 0, 18, 0, 480);

        assertComputeWorkMinutes(9, 0, 11, 59, 179);
        assertComputeWorkMinutes(9, 0, 12, 0, 180);
        assertComputeWorkMinutes(9, 0, 12, 1, 180);
        assertComputeWorkMinutes(9, 0, 12, 59, 180);
        assertComputeWorkMinutes(9, 0, 13, 0, 180);
        assertComputeWorkMinutes(9, 0, 13, 1, 181);
        assertComputeWorkMinutes(9, 0, 17, 59, 479);
        assertComputeWorkMinutes(9, 0, 18, 0, 480);
        assertComputeWorkMinutes(9, 0, 18, 1, 480);
        assertComputeWorkMinutes(9, 0, 18, 59, 480);
        assertComputeWorkMinutes(9, 0, 19, 0, 480);
        assertComputeWorkMinutes(9, 0, 19, 1, 481);
        assertComputeWorkMinutes(9, 0, 20, 59, 599);
        assertComputeWorkMinutes(9, 0, 21, 0, 600);
        assertComputeWorkMinutes(9, 0, 21, 1, 600);
        assertComputeWorkMinutes(9, 0, 21, 59, 600);
        assertComputeWorkMinutes(9, 0, 22, 0, 600);
        assertComputeWorkMinutes(9, 0, 22, 1, 601);
        assertComputeWorkMinutes(9, 0, 23, 59, 719);
        assertComputeWorkMinutes(9, 0, 24, 0, 720);
    }

    @Test
    public void computeComputeOverWorkMinutes() {
        assertComputeOverWorkMinutes(0, 0, 18, 0, 540);
        assertComputeOverWorkMinutes(0, 1, 18, 0, 539);
        assertComputeOverWorkMinutes(8, 59, 18, 0, 1);
        assertComputeOverWorkMinutes(9, 0, 18, 0, 0);

        assertComputeOverWorkMinutes(9, 0, 11, 59, 0);
        assertComputeOverWorkMinutes(9, 0, 12, 0, 0);
        assertComputeOverWorkMinutes(9, 0, 12, 1, 0);
        assertComputeOverWorkMinutes(9, 0, 12, 59, 0);
        assertComputeOverWorkMinutes(9, 0, 13, 0, 0);
        assertComputeOverWorkMinutes(9, 0, 13, 1, 0);
        assertComputeOverWorkMinutes(9, 0, 17, 59, 0);
        assertComputeOverWorkMinutes(9, 0, 18, 0, 0);
        assertComputeOverWorkMinutes(9, 0, 18, 1, 0);
        assertComputeOverWorkMinutes(9, 0, 18, 59, 0);
        assertComputeOverWorkMinutes(9, 0, 19, 0, 0);
        assertComputeOverWorkMinutes(9, 0, 19, 1, 1);
        assertComputeOverWorkMinutes(9, 0, 20, 59, 119);
        assertComputeOverWorkMinutes(9, 0, 21, 0, 120);
        assertComputeOverWorkMinutes(9, 0, 21, 1, 120);
        assertComputeOverWorkMinutes(9, 0, 21, 59, 120);
        assertComputeOverWorkMinutes(9, 0, 22, 0, 120);
        assertComputeOverWorkMinutes(9, 0, 22, 1, 121);
        assertComputeOverWorkMinutes(9, 0, 23, 59, 239);
        assertComputeOverWorkMinutes(9, 0, 24, 0, 240);
    }

    private void assertComputeWorkMinutes(int startHour, int startMinutes, int endHour, int endMinutes, int expected) {
        assertThat(
                new WorkRecord(WORK_DATA, new WorkTime(startHour, startMinutes, endHour, endMinutes)).computeWorkMinutes().getValue(),
                is(equalTo(expected))
        );
    }

    private void assertComputeOverWorkMinutes(int startHour, int startMinutes, int endHour, int endMinutes, int expected) {
        assertThat(
                new WorkRecord(WORK_DATA, new WorkTime(startHour, startMinutes, endHour, endMinutes)).computeOverWorkMinutes().getValue(),
                is(equalTo(expected))
        );
    }

    private void assertGetWorkDateString(int year, int month, int day, String expected) {
        assertThat(new WorkDate(year, month, day).toString(), is(equalTo(expected)));
    }

    private void assertGetStartTimeString(int startHour, int startMinutes, String expected) {
        assertThat(new WorkTime(startHour, startMinutes, 24, 0).getStartTimeString(), is(equalTo(expected)));
    }

    private void assertGetEndTimeString(int ensHour, int ensMinutes, String expected) {
        assertThat(new WorkTime(0, 0, ensHour, ensMinutes).getEndTimeString(), is(equalTo(expected)));
    }
}