package jp.co.esumit.kintai.domain;

import jp.co.esumit.kintai.datasource.csv.ConsoleOutput;
import jp.co.esumit.kintai.datasource.csv.CsvColumns;
import jp.co.esumit.kintai.domain.field.EndTime;
import jp.co.esumit.kintai.domain.field.OfficeMinutes;
import jp.co.esumit.kintai.domain.field.Overtime;
import jp.co.esumit.kintai.domain.field.StartTime;
import jp.co.esumit.kintai.domain.field.TargetDay;
import jp.co.esumit.kintai.domain.field.TotalOfficeMinutes;
import jp.co.esumit.kintai.domain.field.TotalOvertime;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class KintaiDomainOutputDomainTest {


    @Test
    public void n001(){


        List<CsvColumns> inputCsvColsList = this.getCsvColumns(5);

        TotalOfficeMinutes expOfficeMins = new TotalOfficeMinutes(540*5);
        TotalOvertime expOvertime = new TotalOvertime(60*5);

        ConsoleOutput expConsoleOutput = new ConsoleOutput(expOfficeMins,expOvertime);

        this.executeTest(inputCsvColsList,expConsoleOutput,null);

    }

    private void executeTest(List<CsvColumns> inputCsvColsList, ConsoleOutput expConsoleOutput,Exception expException){

        KintaiDomain testClass = new KintaiDomain();

        Exception actException = null;
        ConsoleOutput actConsoleOutput = null;

        try {
            actConsoleOutput = testClass.outputDomain(inputCsvColsList);
        }catch(Exception e){
            actException = e;
        }

        assertEquals("ConsoleOutput",expConsoleOutput,actConsoleOutput);
        assertEquals("Exception",expException,actException);
    }


    private List<CsvColumns> getCsvColumns(int csvCount){

        List<CsvColumns> csvColumnsList = new ArrayList<>();


        for(int i = 0; i < csvCount; i++){

            OfficeMinutes of = new OfficeMinutes(540);
            Overtime ov = new Overtime(60);
            CsvColumns csvCol = new CsvColumns(null,null,null,of,ov,null);
            csvColumnsList.add(csvCol);

        }

        return csvColumnsList;
    }

}
