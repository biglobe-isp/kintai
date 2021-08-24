package jp.co.esumit.kintai.domain.kintai_info;

import jp.co.esumit.kintai.domain.kintai_info.end_time.EndTime;
import jp.co.esumit.kintai.domain.kintai_info.operation_and_working_minutes.ActualWorkingMinutes;
import jp.co.esumit.kintai.domain.kintai_info.overtime_minutes.OvertimeMinutes;
import jp.co.esumit.kintai.domain.kintai_info.registered_time.RegisteredTime;
import jp.co.esumit.kintai.domain.kintai_info.start_time.StartTime;
import jp.co.esumit.kintai.domain.kintai_info.target_day.TargetDay;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class KintaiInfoTest {
    @Test
    public void n001() {
        TargetDay targetDay = new TargetDay("20210101");
        StartTime startTime = new StartTime("0900");
        EndTime endTime = new EndTime("1800");
        ActualWorkingMinutes actualWorkingMinutes = new ActualWorkingMinutes(420);
        OvertimeMinutes overtimeMinutes = new OvertimeMinutes(0);
        RegisteredTime registeredTime = RegisteredTime.create();

        KintaiInfo actualKintaiInfo = KintaiInfo.create(targetDay, startTime, endTime);

        KintaiInfo expectKintaiInfo = new KintaiInfo(
                targetDay
                , startTime
                , endTime
                , actualWorkingMinutes
                , overtimeMinutes
                , registeredTime
        );

        assertEquals("KintaiInfo", expectKintaiInfo, actualKintaiInfo);
    }

    @Test
    public void n002() {
        TargetDay targetDay = new TargetDay("20210102");
        StartTime startTime = new StartTime("0900");
        EndTime endTime = new EndTime("1900");
        ActualWorkingMinutes actualWorkingMinutes = new ActualWorkingMinutes(420);
        OvertimeMinutes overtimeMinutes = new OvertimeMinutes(0);
        RegisteredTime registeredTime = RegisteredTime.create();

        KintaiInfo actualKintaiInfo = KintaiInfo.create(targetDay, startTime, endTime);

        KintaiInfo expectKintaiInfo = new KintaiInfo(
                targetDay
                , startTime
                , endTime
                , actualWorkingMinutes
                , overtimeMinutes
                , registeredTime
        );

        assertEquals("KintaiInfo", expectKintaiInfo, actualKintaiInfo);
    }

    @Test
    public void n003() {
        TargetDay targetDay = new TargetDay("20210103");
        StartTime startTime = new StartTime("0900");
        EndTime endTime = new EndTime("2000");
        ActualWorkingMinutes actualWorkingMinutes = new ActualWorkingMinutes(480);
        OvertimeMinutes overtimeMinutes = new OvertimeMinutes(0);
        RegisteredTime registeredTime = RegisteredTime.create();

        KintaiInfo actualKintaiInfo = KintaiInfo.create(targetDay, startTime, endTime);

        KintaiInfo expectKintaiInfo = new KintaiInfo(
                targetDay
                , startTime
                , endTime
                , actualWorkingMinutes
                , overtimeMinutes
                , registeredTime
        );

        assertEquals("KintaiInfo", expectKintaiInfo, actualKintaiInfo);
    }

    @Test
    public void n004() {
        TargetDay targetDay = new TargetDay("20210104");
        StartTime startTime = new StartTime("0900");
        EndTime endTime = new EndTime("2100");
        ActualWorkingMinutes actualWorkingMinutes = new ActualWorkingMinutes(540);
        OvertimeMinutes overtimeMinutes = new OvertimeMinutes(60);
        RegisteredTime registeredTime = RegisteredTime.create();

        KintaiInfo actualKintaiInfo = KintaiInfo.create(targetDay, startTime, endTime);

        KintaiInfo expectKintaiInfo = new KintaiInfo(
                targetDay
                , startTime
                , endTime
                , actualWorkingMinutes
                , overtimeMinutes
                , registeredTime
        );

        assertEquals("KintaiInfo", expectKintaiInfo, actualKintaiInfo);
    }

    @Test
    public void n005() {
        TargetDay targetDay = new TargetDay("20210105");
        StartTime startTime = new StartTime("0900");
        EndTime endTime = new EndTime("2200");
        ActualWorkingMinutes actualWorkingMinutes = new ActualWorkingMinutes(540);
        OvertimeMinutes overtimeMinutes = new OvertimeMinutes(60);
        RegisteredTime registeredTime = RegisteredTime.create();

        KintaiInfo actualKintaiInfo = KintaiInfo.create(targetDay, startTime, endTime);

        KintaiInfo expectKintaiInfo = new KintaiInfo(
                targetDay
                , startTime
                , endTime
                , actualWorkingMinutes
                , overtimeMinutes
                , registeredTime
        );

        assertEquals("KintaiInfo", expectKintaiInfo, actualKintaiInfo);
    }

    @Test
    public void n006() {
        TargetDay targetDay = new TargetDay("20210106");
        StartTime startTime = new StartTime("0900");
        EndTime endTime = new EndTime("2300");
        ActualWorkingMinutes actualWorkingMinutes = new ActualWorkingMinutes(600);
        OvertimeMinutes overtimeMinutes = new OvertimeMinutes(120);
        RegisteredTime registeredTime = RegisteredTime.create();

        KintaiInfo actualKintaiInfo = KintaiInfo.create(targetDay, startTime, endTime);

        KintaiInfo expectKintaiInfo = new KintaiInfo(
                targetDay
                , startTime
                , endTime
                , actualWorkingMinutes
                , overtimeMinutes
                , registeredTime
        );

        assertEquals("KintaiInfo", expectKintaiInfo, actualKintaiInfo);
    }

    @Test
    public void e007() {
        Exception actualException = null;
        KintaiInfo actualKintaiInfo;
        KintaiInfo expectKintaiInfo;
        try {
            TargetDay targetDay = new TargetDay("20210107");
            StartTime startTime = new StartTime("0900");
            EndTime endTime = new EndTime("2400");
            ActualWorkingMinutes actualWorkingMinutes = new ActualWorkingMinutes(660);
            OvertimeMinutes overtimeMinutes = new OvertimeMinutes(180);
            RegisteredTime registeredTime = RegisteredTime.create();

            actualKintaiInfo = KintaiInfo.create(targetDay, startTime, endTime);

            expectKintaiInfo = new KintaiInfo(
                    targetDay
                    , startTime
                    , endTime
                    , actualWorkingMinutes
                    , overtimeMinutes
                    , registeredTime
            );
        } catch (Exception e) {
            actualException = e;
        }

        assertEquals(
                "Exception Message",
                "Invalid value for HourOfDay (valid values 0 - 23): 24",
                actualException.getMessage()
        );
    }
}