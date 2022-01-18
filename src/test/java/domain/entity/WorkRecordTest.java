package domain.entity;

import domain.value.OverWorkMinutes;
import domain.value.WorkDate;
import domain.value.WorkMinutes;
import domain.value.WorkTime;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class WorkRecordTest {
    private WorkDate WORK_DATE_BEFORE_BREAK_TIME_CHANGE = new WorkDate(2022, 2, 14);
    private WorkDate WORK_DATE_AFTER_BREAK_TIME_CHANGE = new WorkDate(2022, 2, 15);

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
        assertComputeWorkMinutes(WORK_DATE_BEFORE_BREAK_TIME_CHANGE,  0, 0, 18, 0, new WorkMinutes(1020));
        assertComputeWorkMinutes(WORK_DATE_BEFORE_BREAK_TIME_CHANGE,  0, 1, 18, 0, new WorkMinutes(1019));
        assertComputeWorkMinutes(WORK_DATE_BEFORE_BREAK_TIME_CHANGE,  8, 59, 18, 0, new WorkMinutes(481));
        assertComputeWorkMinutes(WORK_DATE_BEFORE_BREAK_TIME_CHANGE,  9, 0, 18, 0, new WorkMinutes(480));

        assertComputeWorkMinutes(WORK_DATE_BEFORE_BREAK_TIME_CHANGE,  9, 0, 11, 59, new WorkMinutes(179));
        assertComputeWorkMinutes(WORK_DATE_BEFORE_BREAK_TIME_CHANGE,  9, 0, 12, 0, new WorkMinutes(180));
        assertComputeWorkMinutes(WORK_DATE_BEFORE_BREAK_TIME_CHANGE,  9, 0, 12, 1, new WorkMinutes(180));
        assertComputeWorkMinutes(WORK_DATE_BEFORE_BREAK_TIME_CHANGE,  9, 0, 12, 59, new WorkMinutes(180));
        assertComputeWorkMinutes(WORK_DATE_BEFORE_BREAK_TIME_CHANGE,  9, 0, 13, 0, new WorkMinutes(180));
        assertComputeWorkMinutes(WORK_DATE_BEFORE_BREAK_TIME_CHANGE,  9, 0, 13, 1, new WorkMinutes(181));
        assertComputeWorkMinutes(WORK_DATE_BEFORE_BREAK_TIME_CHANGE,  9, 0, 17, 59, new WorkMinutes(479));
        assertComputeWorkMinutes(WORK_DATE_BEFORE_BREAK_TIME_CHANGE,  9, 0, 18, 0, new WorkMinutes(480));
        assertComputeWorkMinutes(WORK_DATE_BEFORE_BREAK_TIME_CHANGE,  9, 0, 18, 1, new WorkMinutes(480));
        assertComputeWorkMinutes(WORK_DATE_BEFORE_BREAK_TIME_CHANGE,  9, 0, 18, 59, new WorkMinutes(480));
        assertComputeWorkMinutes(WORK_DATE_BEFORE_BREAK_TIME_CHANGE,  9, 0, 19, 0, new WorkMinutes(480));
        assertComputeWorkMinutes(WORK_DATE_BEFORE_BREAK_TIME_CHANGE,  9, 0, 19, 1, new WorkMinutes(481));
        assertComputeWorkMinutes(WORK_DATE_BEFORE_BREAK_TIME_CHANGE,  9, 0, 20, 59, new WorkMinutes(599));
        assertComputeWorkMinutes(WORK_DATE_BEFORE_BREAK_TIME_CHANGE,  9, 0, 21, 0, new WorkMinutes(600));
        assertComputeWorkMinutes(WORK_DATE_BEFORE_BREAK_TIME_CHANGE,  9, 0, 21, 1, new WorkMinutes(600));
        assertComputeWorkMinutes(WORK_DATE_BEFORE_BREAK_TIME_CHANGE,  9, 0, 21, 59, new WorkMinutes(600));
        assertComputeWorkMinutes(WORK_DATE_BEFORE_BREAK_TIME_CHANGE,  9, 0, 22, 0, new WorkMinutes(600));
        assertComputeWorkMinutes(WORK_DATE_BEFORE_BREAK_TIME_CHANGE,  9, 0, 22, 1, new WorkMinutes(601));
        assertComputeWorkMinutes(WORK_DATE_BEFORE_BREAK_TIME_CHANGE,  9, 0, 23, 59, new WorkMinutes(719));
        assertComputeWorkMinutes(WORK_DATE_BEFORE_BREAK_TIME_CHANGE,  9, 0, 24, 0, new WorkMinutes(720));


        assertComputeWorkMinutes(WORK_DATE_AFTER_BREAK_TIME_CHANGE,  0, 0, 18, 0, new WorkMinutes(960));
        assertComputeWorkMinutes(WORK_DATE_AFTER_BREAK_TIME_CHANGE,  0, 1, 18, 0, new WorkMinutes(959));
        assertComputeWorkMinutes(WORK_DATE_AFTER_BREAK_TIME_CHANGE,  8, 59, 18, 0, new WorkMinutes(421));
        assertComputeWorkMinutes(WORK_DATE_AFTER_BREAK_TIME_CHANGE,  9, 0, 18, 0, new WorkMinutes(420));

        assertComputeWorkMinutes(WORK_DATE_AFTER_BREAK_TIME_CHANGE,  9, 0, 11, 59, new WorkMinutes(179));
        assertComputeWorkMinutes(WORK_DATE_AFTER_BREAK_TIME_CHANGE,  9, 0, 12, 0, new WorkMinutes(180));
        assertComputeWorkMinutes(WORK_DATE_AFTER_BREAK_TIME_CHANGE,  9, 0, 12, 1, new WorkMinutes(180));
        assertComputeWorkMinutes(WORK_DATE_AFTER_BREAK_TIME_CHANGE,  9, 0, 12, 59, new WorkMinutes(180));
        assertComputeWorkMinutes(WORK_DATE_AFTER_BREAK_TIME_CHANGE,  9, 0, 13, 0, new WorkMinutes(180));
        assertComputeWorkMinutes(WORK_DATE_AFTER_BREAK_TIME_CHANGE,  9, 0, 13, 1, new WorkMinutes(181));
        assertComputeWorkMinutes(WORK_DATE_AFTER_BREAK_TIME_CHANGE,  9, 0, 14, 59, new WorkMinutes(299));
        assertComputeWorkMinutes(WORK_DATE_AFTER_BREAK_TIME_CHANGE,  9, 0, 15, 0, new WorkMinutes(300));
        assertComputeWorkMinutes(WORK_DATE_AFTER_BREAK_TIME_CHANGE,  9, 0, 15, 1, new WorkMinutes(300));
        assertComputeWorkMinutes(WORK_DATE_AFTER_BREAK_TIME_CHANGE,  9, 0, 15, 59, new WorkMinutes(300));
        assertComputeWorkMinutes(WORK_DATE_AFTER_BREAK_TIME_CHANGE,  9, 0, 16, 0, new WorkMinutes(300));
        assertComputeWorkMinutes(WORK_DATE_AFTER_BREAK_TIME_CHANGE,  9, 0, 16, 1, new WorkMinutes(301));
        assertComputeWorkMinutes(WORK_DATE_AFTER_BREAK_TIME_CHANGE,  9, 0, 17, 59, new WorkMinutes(419));
        assertComputeWorkMinutes(WORK_DATE_AFTER_BREAK_TIME_CHANGE,  9, 0, 18, 0, new WorkMinutes(420));
        assertComputeWorkMinutes(WORK_DATE_AFTER_BREAK_TIME_CHANGE,  9, 0, 18, 1, new WorkMinutes(420));
        assertComputeWorkMinutes(WORK_DATE_AFTER_BREAK_TIME_CHANGE,  9, 0, 18, 59, new WorkMinutes(420));
        assertComputeWorkMinutes(WORK_DATE_AFTER_BREAK_TIME_CHANGE,  9, 0, 19, 0, new WorkMinutes(420));
        assertComputeWorkMinutes(WORK_DATE_AFTER_BREAK_TIME_CHANGE,  9, 0, 19, 1, new WorkMinutes(421));
        assertComputeWorkMinutes(WORK_DATE_AFTER_BREAK_TIME_CHANGE,  9, 0, 20, 59, new WorkMinutes(539));
        assertComputeWorkMinutes(WORK_DATE_AFTER_BREAK_TIME_CHANGE,  9, 0, 21, 0, new WorkMinutes(540));
        assertComputeWorkMinutes(WORK_DATE_AFTER_BREAK_TIME_CHANGE,  9, 0, 21, 1, new WorkMinutes(540));
        assertComputeWorkMinutes(WORK_DATE_AFTER_BREAK_TIME_CHANGE,  9, 0, 21, 59, new WorkMinutes(540));
        assertComputeWorkMinutes(WORK_DATE_AFTER_BREAK_TIME_CHANGE,  9, 0, 22, 0, new WorkMinutes(540));
        assertComputeWorkMinutes(WORK_DATE_AFTER_BREAK_TIME_CHANGE,  9, 0, 22, 1, new WorkMinutes(541));
        assertComputeWorkMinutes(WORK_DATE_AFTER_BREAK_TIME_CHANGE,  9, 0, 23, 59, new WorkMinutes(659));
        assertComputeWorkMinutes(WORK_DATE_AFTER_BREAK_TIME_CHANGE,  9, 0, 24, 0, new WorkMinutes(660));
    }

    @Test
    public void computeComputeOverWorkMinutes() {
        assertComputeOverWorkMinutes(WORK_DATE_BEFORE_BREAK_TIME_CHANGE,  0, 0, 18, 0, new OverWorkMinutes(540));
        assertComputeOverWorkMinutes(WORK_DATE_BEFORE_BREAK_TIME_CHANGE,  0, 1, 18, 0, new OverWorkMinutes(539));
        assertComputeOverWorkMinutes(WORK_DATE_BEFORE_BREAK_TIME_CHANGE,  8, 59, 18, 0, new OverWorkMinutes(1));
        assertComputeOverWorkMinutes(WORK_DATE_BEFORE_BREAK_TIME_CHANGE,  9, 0, 18, 0, new OverWorkMinutes(0));

        assertComputeOverWorkMinutes(WORK_DATE_BEFORE_BREAK_TIME_CHANGE,  9, 0, 11, 59, new OverWorkMinutes(0));
        assertComputeOverWorkMinutes(WORK_DATE_BEFORE_BREAK_TIME_CHANGE,  9, 0, 12, 0, new OverWorkMinutes(0));
        assertComputeOverWorkMinutes(WORK_DATE_BEFORE_BREAK_TIME_CHANGE,  9, 0, 12, 1, new OverWorkMinutes(0));
        assertComputeOverWorkMinutes(WORK_DATE_BEFORE_BREAK_TIME_CHANGE,  9, 0, 12, 59, new OverWorkMinutes(0));
        assertComputeOverWorkMinutes(WORK_DATE_BEFORE_BREAK_TIME_CHANGE,  9, 0, 13, 0, new OverWorkMinutes(0));
        assertComputeOverWorkMinutes(WORK_DATE_BEFORE_BREAK_TIME_CHANGE,  9, 0, 13, 1, new OverWorkMinutes(0));
        assertComputeOverWorkMinutes(WORK_DATE_BEFORE_BREAK_TIME_CHANGE,  9, 0, 17, 59, new OverWorkMinutes(0));
        assertComputeOverWorkMinutes(WORK_DATE_BEFORE_BREAK_TIME_CHANGE,  9, 0, 18, 0, new OverWorkMinutes(0));
        assertComputeOverWorkMinutes(WORK_DATE_BEFORE_BREAK_TIME_CHANGE,  9, 0, 18, 1, new OverWorkMinutes(0));
        assertComputeOverWorkMinutes(WORK_DATE_BEFORE_BREAK_TIME_CHANGE,  9, 0, 18, 59, new OverWorkMinutes(0));
        assertComputeOverWorkMinutes(WORK_DATE_BEFORE_BREAK_TIME_CHANGE,  9, 0, 19, 0, new OverWorkMinutes(0));
        assertComputeOverWorkMinutes(WORK_DATE_BEFORE_BREAK_TIME_CHANGE,  9, 0, 19, 1, new OverWorkMinutes(1));
        assertComputeOverWorkMinutes(WORK_DATE_BEFORE_BREAK_TIME_CHANGE,  9, 0, 20, 59, new OverWorkMinutes(119));
        assertComputeOverWorkMinutes(WORK_DATE_BEFORE_BREAK_TIME_CHANGE,  9, 0, 21, 0, new OverWorkMinutes(120));
        assertComputeOverWorkMinutes(WORK_DATE_BEFORE_BREAK_TIME_CHANGE,  9, 0, 21, 1, new OverWorkMinutes(120));
        assertComputeOverWorkMinutes(WORK_DATE_BEFORE_BREAK_TIME_CHANGE,  9, 0, 21, 59, new OverWorkMinutes(120));
        assertComputeOverWorkMinutes(WORK_DATE_BEFORE_BREAK_TIME_CHANGE,  9, 0, 22, 0, new OverWorkMinutes(120));
        assertComputeOverWorkMinutes(WORK_DATE_BEFORE_BREAK_TIME_CHANGE,  9, 0, 22, 1, new OverWorkMinutes(121));
        assertComputeOverWorkMinutes(WORK_DATE_BEFORE_BREAK_TIME_CHANGE,  9, 0, 23, 59, new OverWorkMinutes(239));
        assertComputeOverWorkMinutes(WORK_DATE_BEFORE_BREAK_TIME_CHANGE,  9, 0, 24, 0, new OverWorkMinutes(240));


        assertComputeOverWorkMinutes(WORK_DATE_AFTER_BREAK_TIME_CHANGE,  0, 0, 18, 0, new OverWorkMinutes(480));
        assertComputeOverWorkMinutes(WORK_DATE_AFTER_BREAK_TIME_CHANGE,  0, 1, 18, 0, new OverWorkMinutes(479));
        assertComputeOverWorkMinutes(WORK_DATE_AFTER_BREAK_TIME_CHANGE,  8, 59, 18, 0, new OverWorkMinutes(0));
        assertComputeOverWorkMinutes(WORK_DATE_AFTER_BREAK_TIME_CHANGE,  9, 0, 18, 0, new OverWorkMinutes(0));

        assertComputeOverWorkMinutes(WORK_DATE_AFTER_BREAK_TIME_CHANGE,  9, 0, 11, 59, new OverWorkMinutes(0));
        assertComputeOverWorkMinutes(WORK_DATE_AFTER_BREAK_TIME_CHANGE,  9, 0, 12, 0, new OverWorkMinutes(0));
        assertComputeOverWorkMinutes(WORK_DATE_AFTER_BREAK_TIME_CHANGE,  9, 0, 12, 1, new OverWorkMinutes(0));
        assertComputeOverWorkMinutes(WORK_DATE_AFTER_BREAK_TIME_CHANGE,  9, 0, 12, 59, new OverWorkMinutes(0));
        assertComputeOverWorkMinutes(WORK_DATE_AFTER_BREAK_TIME_CHANGE,  9, 0, 13, 0, new OverWorkMinutes(0));
        assertComputeOverWorkMinutes(WORK_DATE_AFTER_BREAK_TIME_CHANGE,  9, 0, 13, 1, new OverWorkMinutes(0));
        assertComputeOverWorkMinutes(WORK_DATE_AFTER_BREAK_TIME_CHANGE,  9, 0, 14, 59, new OverWorkMinutes(0));
        assertComputeOverWorkMinutes(WORK_DATE_AFTER_BREAK_TIME_CHANGE,  9, 0, 15, 0, new OverWorkMinutes(0));
        assertComputeOverWorkMinutes(WORK_DATE_AFTER_BREAK_TIME_CHANGE,  9, 0, 15, 1, new OverWorkMinutes(0));
        assertComputeOverWorkMinutes(WORK_DATE_AFTER_BREAK_TIME_CHANGE,  9, 0, 15, 59, new OverWorkMinutes(0));
        assertComputeOverWorkMinutes(WORK_DATE_AFTER_BREAK_TIME_CHANGE,  9, 0, 16, 0, new OverWorkMinutes(0));
        assertComputeOverWorkMinutes(WORK_DATE_AFTER_BREAK_TIME_CHANGE,  9, 0, 16, 1, new OverWorkMinutes(0));
        assertComputeOverWorkMinutes(WORK_DATE_AFTER_BREAK_TIME_CHANGE,  9, 0, 17, 59, new OverWorkMinutes(0));
        assertComputeOverWorkMinutes(WORK_DATE_AFTER_BREAK_TIME_CHANGE,  9, 0, 18, 0, new OverWorkMinutes(0));
        assertComputeOverWorkMinutes(WORK_DATE_AFTER_BREAK_TIME_CHANGE,  9, 0, 18, 1, new OverWorkMinutes(0));
        assertComputeOverWorkMinutes(WORK_DATE_AFTER_BREAK_TIME_CHANGE,  9, 0, 18, 59, new OverWorkMinutes(0));
        assertComputeOverWorkMinutes(WORK_DATE_AFTER_BREAK_TIME_CHANGE,  9, 0, 19, 0, new OverWorkMinutes(0));
        assertComputeOverWorkMinutes(WORK_DATE_AFTER_BREAK_TIME_CHANGE,  9, 0, 19, 1, new OverWorkMinutes(0));
        assertComputeOverWorkMinutes(WORK_DATE_AFTER_BREAK_TIME_CHANGE,  9, 0, 20, 59, new OverWorkMinutes(59));
        assertComputeOverWorkMinutes(WORK_DATE_AFTER_BREAK_TIME_CHANGE,  9, 0, 21, 0, new OverWorkMinutes(60));
        assertComputeOverWorkMinutes(WORK_DATE_AFTER_BREAK_TIME_CHANGE,  9, 0, 21, 1, new OverWorkMinutes(60));
        assertComputeOverWorkMinutes(WORK_DATE_AFTER_BREAK_TIME_CHANGE,  9, 0, 21, 59, new OverWorkMinutes(60));
        assertComputeOverWorkMinutes(WORK_DATE_AFTER_BREAK_TIME_CHANGE,  9, 0, 22, 0, new OverWorkMinutes(60));
        assertComputeOverWorkMinutes(WORK_DATE_AFTER_BREAK_TIME_CHANGE,  9, 0, 22, 1, new OverWorkMinutes(61));
        assertComputeOverWorkMinutes(WORK_DATE_AFTER_BREAK_TIME_CHANGE,  9, 0, 23, 59, new OverWorkMinutes(179));
        assertComputeOverWorkMinutes(WORK_DATE_AFTER_BREAK_TIME_CHANGE,  9, 0, 24, 0, new OverWorkMinutes(180));
    }

    private void assertComputeWorkMinutes(WorkDate workDate, int startHour, int startMinutes, int endHour, int endMinutes, WorkMinutes expected) {
        assertThat(
                new WorkRecord(workDate, new WorkTime(startHour, startMinutes, endHour, endMinutes)).computeWorkMinutes(),
                is(equalTo(expected))
        );
    }

    private void assertComputeOverWorkMinutes(WorkDate workDate, int startHour, int startMinutes, int endHour, int endMinutes, OverWorkMinutes expected) {
        assertThat(
                new WorkRecord(workDate, new WorkTime(startHour, startMinutes, endHour, endMinutes)).computeOverWorkMinutes(),
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