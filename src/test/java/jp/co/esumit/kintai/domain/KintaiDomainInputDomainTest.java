package jp.co.esumit.kintai.domain;

import jp.co.esumit.kintai.domain.kintai_info.KintaiInfo;
import jp.co.esumit.kintai.domain.kintai_info.end_time.EndTime;
import jp.co.esumit.kintai.domain.kintai_info.office_minutes.OfficeMinutes;
import jp.co.esumit.kintai.domain.kintai_info.overtime_minutes.OvertimeMinutes;
import jp.co.esumit.kintai.domain.kintai_info.start_time.StartTime;
import jp.co.esumit.kintai.domain.kintai_info.target_day.TargetDay;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class KintaiDomainInputDomainTest {
    @Test
    public void n001() {

        TargetDay inputTargetDay = new TargetDay("20210101");
        StartTime inputStartTime = new StartTime("0900");
        EndTime inputEndTime = new EndTime("1700");

        OfficeMinutes expOfficeMins = new OfficeMinutes(360);
        OvertimeMinutes expOvertimeMinutes = new OvertimeMinutes(0);

        this.executeTest(inputTargetDay, inputStartTime, inputEndTime, expOfficeMins, expOvertimeMinutes, null);
    }

    @Test
    public void n002() {

        TargetDay inputTargetDay = new TargetDay("20210101");
        StartTime inputStartTime = new StartTime("0900");
        EndTime inputEndTime = new EndTime("1800");

        OfficeMinutes expOfficeMins = new OfficeMinutes(420);
        OvertimeMinutes expOvertimeMinutes = new OvertimeMinutes(0);

        this.executeTest(inputTargetDay, inputStartTime, inputEndTime, expOfficeMins, expOvertimeMinutes, null);
    }

    @Test
    public void n003() {

        TargetDay inputTargetDay = new TargetDay("20210101");
        StartTime inputStartTime = new StartTime("0900");
        EndTime inputEndTime = new EndTime("1900");

        OfficeMinutes expOfficeMins = new OfficeMinutes(420);
        OvertimeMinutes expOvertimeMinutes = new OvertimeMinutes(0);

        this.executeTest(inputTargetDay, inputStartTime, inputEndTime, expOfficeMins, expOvertimeMinutes, null);
    }

    @Test
    public void n004() {

        TargetDay inputTargetDay = new TargetDay("20210101");
        StartTime inputStartTime = new StartTime("0900");
        EndTime inputEndTime = new EndTime("2000");

        OfficeMinutes expOfficeMins = new OfficeMinutes(480);
        OvertimeMinutes expOvertimeMinutes = new OvertimeMinutes(0);

        this.executeTest(inputTargetDay, inputStartTime, inputEndTime, expOfficeMins, expOvertimeMinutes, null);
    }

    @Test
    public void n005() {

        TargetDay inputTargetDay = new TargetDay("20210101");
        StartTime inputStartTime = new StartTime("0900");
        EndTime inputEndTime = new EndTime("2100");

        OfficeMinutes expOfficeMins = new OfficeMinutes(540);
        OvertimeMinutes expOvertimeMinutes = new OvertimeMinutes(60);

        this.executeTest(inputTargetDay, inputStartTime, inputEndTime, expOfficeMins, expOvertimeMinutes, null);
    }

    @Test
    public void n006() {

        TargetDay inputTargetDay = new TargetDay("20210101");
        StartTime inputStartTime = new StartTime("0900");
        EndTime inputEndTime = new EndTime("2200");

        OfficeMinutes expOfficeMins = new OfficeMinutes(540);
        OvertimeMinutes expOvertimeMinutes = new OvertimeMinutes(60);

        this.executeTest(inputTargetDay, inputStartTime, inputEndTime, expOfficeMins, expOvertimeMinutes, null);
    }

    private void executeTest(
            TargetDay inputTargetDay, StartTime inputStartTime, EndTime inputEndTime,
            OfficeMinutes expOfficeMins, OvertimeMinutes expOvertimeMinutes, Exception expException) {

        //KintaiDomain testClass = new KintaiDomain();

        Exception actException = null;
        KintaiInfo actKintaiInfo = null;

        try {
            //actDataColumns = testClass.inputDomain(inputTargetDay, inputStartTime, inputEndTime);
        } catch (Exception e) {
            actException = e;
        }

        assertEquals("TargetDay", inputTargetDay, actKintaiInfo.getTargetDay());
        assertEquals("StartTime", inputStartTime, actKintaiInfo.getStartTime());
        assertEquals("EndTime", inputEndTime, actKintaiInfo.getEndTime());
        assertEquals("OfficeMinutes", expOfficeMins, actKintaiInfo.getOfficeMinutes());
        assertEquals("OvertimeMinutes", expOvertimeMinutes, actKintaiInfo.getOvertimeMinutes());
        assertEquals("Exception", expException, actException);
    }
}
