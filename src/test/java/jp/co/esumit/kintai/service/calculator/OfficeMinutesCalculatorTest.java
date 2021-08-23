package jp.co.esumit.kintai.service.calculator;

import jp.co.esumit.kintai.domain.kintai_info.end_time.EndTime;
import jp.co.esumit.kintai.domain.kintai_info.operation_and_working_minutes.OperatingMinutes;
import jp.co.esumit.kintai.domain.kintai_info.start_time.StartTime;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class OfficeMinutesCalculatorTest {
    //  ActualWorkingMinutesCalculator testClass = new ActualWorkingMinutesCalculator();

    @Test
    public void n001() {

        StartTime inputStartTime = new StartTime("0900");
        EndTime inputEndTime = new EndTime("1800");

        // OperatingMinutes expOperatingMinutes = new OperatingMinutes(7 * 60);
        Exception expException = null;

        //   OperatingMinutes actOperatingMinutes = testClass.calc(inputStartTime, inputEndTime);


        //    assertEquals("OperatingMinutes", expOperatingMinutes, actOperatingMinutes);
    }

    @Test
    public void e002() {

        StartTime inputStartTime = new StartTime("-0900");
        EndTime inputEndTime = new EndTime("1800");

        try {
            //       testClass.calc(inputStartTime, inputEndTime);
        } catch (IllegalArgumentException e) {
            return;
        }
        fail();
    }

    private void executeTest(
            StartTime inputStartTime, EndTime inputEndTime,
            OperatingMinutes expOperatingMinutes, Exception expException) {

        //  ActualWorkingMinutesCalculator testClass = new ActualWorkingMinutesCalculator();

        Exception actException = null;
        OperatingMinutes actOperatingMinutes = null;

        try {
            //     actOperatingMinutes = testClass.calc(inputStartTime, inputEndTime);
        } catch (Exception e) {
            assertEquals("Exception", expException, e);
        }

        assertEquals("OperatingMinutes", expOperatingMinutes, actOperatingMinutes);
    }
}
