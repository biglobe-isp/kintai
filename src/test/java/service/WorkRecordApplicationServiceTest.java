package service;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class WorkRecordApplicationServiceTest {
    private WorkRecordApplicationService workRecordApplicationService
            = new WorkRecordApplicationService(new TestWorkRecordRepository());

    @Test
    public void computeTotalWorkMinutes() {
        assertComputeTotalWorkMinutes(2022, 1, 0, 0);
        assertComputeTotalWorkMinutes(2022, 2, 0, 0);
        workRecordApplicationService.inputWorkRecord(2022, 1, 14, 9, 0, 18, 0);
        assertComputeTotalWorkMinutes(2022, 1, 480, 0);
        workRecordApplicationService.inputWorkRecord(2022, 1, 15, 9, 0, 21, 0);
        assertComputeTotalWorkMinutes(2022, 1, 1080, 120);

        assertComputeTotalWorkMinutes(2022, 2, 0, 0);
        workRecordApplicationService.inputWorkRecord(2022, 2, 14, 9, 0, 18, 0);
        assertComputeTotalWorkMinutes(2022, 2, 480, 0);
        workRecordApplicationService.inputWorkRecord(2022, 2, 15, 9, 0, 21, 0);
        assertComputeTotalWorkMinutes(2022, 2, 1080, 120);

        assertComputeTotalWorkMinutes(2022, 1, 1080, 120);

        assertComputeTotalWorkMinutes(2022, 3, 0, 0);
        workRecordApplicationService.inputWorkRecord(2022, 3, 14, 5, 0, 20, 0);
        assertComputeTotalWorkMinutes(2022, 3, 780, 300);
        workRecordApplicationService.inputWorkRecord(2022, 3, 15, 5, 0, 23, 0);
        assertComputeTotalWorkMinutes(2022, 3, 1680, 720);
    }

    private void assertComputeTotalWorkMinutes(int year, int month, int expectedWorkMinutes, int expectedOverWorkMinutes) {
       TotalWorkMinutesModel totalWorkMinutesModel = workRecordApplicationService.computeTotalWorkMinutes(year, month);
       assertThat(totalWorkMinutesModel.getTotalWorkMinutes(), is(equalTo(expectedWorkMinutes)));
       assertThat(totalWorkMinutesModel.getTotalOverWorkMinutes(), is(equalTo(expectedOverWorkMinutes)));
    }
}