package jp.co.esumit.kintai.domain;

import jp.co.esumit.kintai.datasource.csv.ConsoleOutput;
import jp.co.esumit.kintai.datasource.csv.CsvColumns;
import jp.co.esumit.kintai.domain.field.EndTime;
import jp.co.esumit.kintai.domain.field.OfficeMinutes;
import jp.co.esumit.kintai.domain.field.Overtime;
import jp.co.esumit.kintai.domain.field.RegisteredTime;
import jp.co.esumit.kintai.domain.field.StartTime;
import jp.co.esumit.kintai.domain.field.TargetDay;
import jp.co.esumit.kintai.domain.field.TotalOfficeMinutes;
import jp.co.esumit.kintai.domain.field.TotalOvertime;

import java.util.List;

public class KintaiDomain {

    public CsvColumns inputDomain(TargetDay targetDay, StartTime startTime, EndTime endtime){

        OfficeMinutes officeMinutes = new CalcOfficeMinutes().getOfficeMinutes(startTime, endtime);
        Overtime overtime = new CalcOvertime().getOverTime(officeMinutes);
        RegisteredTime registeredTime = new CalcRegisteredTime().getRegisteredTime();

        return new CsvColumns(targetDay,startTime,endtime,officeMinutes,overtime,registeredTime);
    }


    public ConsoleOutput outputDomain(List<CsvColumns> targetList){

        TotalOfficeMinutes totalOfficeMinutes = new TotalOfficeMinutes(
                targetList.stream()
                        .mapToInt(csvColumns -> csvColumns.getOfficeMinutes().getValue()).reduce(0,Integer::sum));

        TotalOvertime totalOvertime = new TotalOvertime(
                targetList.stream()
                        .mapToInt(csvColumns -> csvColumns.getOvertime().getValue()).reduce(0,Integer::sum));

        return new ConsoleOutput(totalOfficeMinutes,totalOvertime);
    }

}
