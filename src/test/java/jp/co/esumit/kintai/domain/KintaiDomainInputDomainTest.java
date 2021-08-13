package jp.co.esumit.kintai.domain;

import jp.co.esumit.kintai.datasource.csv.CsvColumns;
import jp.co.esumit.kintai.domain.field.EndTime;
import jp.co.esumit.kintai.domain.field.OfficeMinutes;
import jp.co.esumit.kintai.domain.field.Overtime;
import jp.co.esumit.kintai.domain.field.StartTime;
import jp.co.esumit.kintai.domain.field.TargetDay;
import org.junit.Test;


import static org.junit.Assert.assertEquals;

public class KintaiDomainInputDomainTest {


    @Test
    public void n001(){

        TargetDay inputTargetDay = new TargetDay("20210101");
        StartTime inputStartTime = new StartTime("0900");
        EndTime inputEndTime = new EndTime("1700");

        OfficeMinutes expOfficeMins = new OfficeMinutes(360);
        Overtime expOvertime = new Overtime(0);

        this.executeTest(inputTargetDay,inputStartTime,inputEndTime,expOfficeMins,expOvertime,null);

    }

    @Test
    public void n002(){

        TargetDay inputTargetDay = new TargetDay("20210101");
        StartTime inputStartTime = new StartTime("0900");
        EndTime inputEndTime = new EndTime("1800");

        OfficeMinutes expOfficeMins = new OfficeMinutes(420);
        Overtime expOvertime = new Overtime(0);

        this.executeTest(inputTargetDay,inputStartTime,inputEndTime,expOfficeMins,expOvertime,null);

    }

    @Test
    public void n003(){

        TargetDay inputTargetDay = new TargetDay("20210101");
        StartTime inputStartTime = new StartTime("0900");
        EndTime inputEndTime = new EndTime("1900");

        OfficeMinutes expOfficeMins = new OfficeMinutes(420);
        Overtime expOvertime = new Overtime(0);

        this.executeTest(inputTargetDay,inputStartTime,inputEndTime,expOfficeMins,expOvertime,null);

    }

    @Test
    public void n004(){

        TargetDay inputTargetDay = new TargetDay("20210101");
        StartTime inputStartTime = new StartTime("0900");
        EndTime inputEndTime = new EndTime("2000");

        OfficeMinutes expOfficeMins = new OfficeMinutes(480);
        Overtime expOvertime = new Overtime(0);

        this.executeTest(inputTargetDay,inputStartTime,inputEndTime,expOfficeMins,expOvertime,null);

    }

    @Test
    public void n005(){

        TargetDay inputTargetDay = new TargetDay("20210101");
        StartTime inputStartTime = new StartTime("0900");
        EndTime inputEndTime = new EndTime("2100");

        OfficeMinutes expOfficeMins = new OfficeMinutes(540);
        Overtime expOvertime = new Overtime(60);

        this.executeTest(inputTargetDay,inputStartTime,inputEndTime,expOfficeMins,expOvertime,null);

    }

    @Test
    public void n006(){

        TargetDay inputTargetDay = new TargetDay("20210101");
        StartTime inputStartTime = new StartTime("0900");
        EndTime inputEndTime = new EndTime("2200");

        OfficeMinutes expOfficeMins = new OfficeMinutes(540);
        Overtime expOvertime = new Overtime(60);

        this.executeTest(inputTargetDay,inputStartTime,inputEndTime,expOfficeMins,expOvertime,null);

    }

    private void executeTest(TargetDay inputTargetDay,StartTime inputStartTime, EndTime inputEndTime,
                             OfficeMinutes expOfficeMins,Overtime expOvertime,Exception expException){

        KintaiDomain testClass = new KintaiDomain();

        Exception actException = null;
        CsvColumns actCsvColumns = null;

        try {
            actCsvColumns = testClass.inputDomain(inputTargetDay,inputStartTime,inputEndTime);
        }catch(Exception e){
            actException = e;
        }

        assertEquals("TargetDay",inputTargetDay,actCsvColumns.getTargetDay());
        assertEquals("StartTime",inputStartTime,actCsvColumns.getStartTime());
        assertEquals("EndTime",inputEndTime,actCsvColumns.getEndTime());
        assertEquals("OfficeMinutes",expOfficeMins,actCsvColumns.getOfficeMinutes());
        assertEquals("Overtime",expOvertime,actCsvColumns.getOvertime());
        assertEquals("Exception",expException,actException);
    }

}
