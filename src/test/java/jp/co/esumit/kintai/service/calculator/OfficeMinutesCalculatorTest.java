package jp.co.esumit.kintai.service.calculator;

import jp.co.esumit.kintai.domain.kintai_info.end_time.EndTime;
import jp.co.esumit.kintai.domain.kintai_info.office_minutes.OfficeMinutes;
import jp.co.esumit.kintai.domain.kintai_info.office_minutes.OfficeMinutesCalculator;
import jp.co.esumit.kintai.domain.kintai_info.start_time.StartTime;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class OfficeMinutesCalculatorTest {
    OfficeMinutesCalculator testClass = new OfficeMinutesCalculator();

    @Test
    public void n001() {

        StartTime inputStartTime = new StartTime("0900");
        EndTime inputEndTime = new EndTime("1800");

        OfficeMinutes expOfficeMinutes = new OfficeMinutes(7 * 60);
        Exception expException = null;

        OfficeMinutes actOfficeMinutes = testClass.calc(inputStartTime, inputEndTime);


        assertEquals("OfficeMinutes", expOfficeMinutes, actOfficeMinutes);
    }

    @Test
    public void e002() {

        StartTime inputStartTime = new StartTime("-0900");
        EndTime inputEndTime = new EndTime("1800");

        try {
            testClass.calc(inputStartTime, inputEndTime);
        } catch (IllegalArgumentException e) {
            return;
        }
        fail();
    }

    private void executeTest(
            StartTime inputStartTime, EndTime inputEndTime,
            OfficeMinutes expOfficeMinutes, Exception expException) {

        OfficeMinutesCalculator testClass = new OfficeMinutesCalculator();

        Exception actException = null;
        OfficeMinutes actOfficeMinutes = null;

        try {
            actOfficeMinutes = testClass.calc(inputStartTime, inputEndTime);
        } catch (Exception e) {
            assertEquals("Exception", expException, e);
        }

        assertEquals("OfficeMinutes", expOfficeMinutes, actOfficeMinutes);
    }
}
