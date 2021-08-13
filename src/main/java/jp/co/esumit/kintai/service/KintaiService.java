package jp.co.esumit.kintai.service;

import jp.co.esumit.kintai.datasource.csv.ConsoleOutput;
import jp.co.esumit.kintai.datasource.csv.CsvColumns;
import jp.co.esumit.kintai.datasource.csv.Reader;
import jp.co.esumit.kintai.datasource.csv.Writer;
import jp.co.esumit.kintai.domain.field.EndTime;
import jp.co.esumit.kintai.domain.KintaiDomain;
import jp.co.esumit.kintai.domain.field.StartTime;
import jp.co.esumit.kintai.domain.field.TargetDay;
import jp.co.esumit.kintai.domain.field.TargetYearMonth;

public class KintaiService {

    KintaiDomain domain = new KintaiDomain();

    public void inputService(TargetDay targetDay, StartTime startTime, EndTime endTime){

        CsvColumns columns = domain.inputDomain(targetDay, startTime, endTime);
        Writer writer = new Writer();
        writer.writer(columns);
    }

    public void totalService(TargetYearMonth targetYM){

        Reader reader = new Reader();
        ConsoleOutput output = domain.outputDomain(reader.reader(targetYM));

        System.out.println("勤務時間: " + output.getTotalOfficeMinutes().getValue() / 60 + "時間"
                                   + output.getTotalOfficeMinutes().getValue() % 60 + "分");
        System.out.println("残業時間: " + output.getTotalOvertime().getValue() / 60 + "時間"
                                   + output.getTotalOvertime().getValue() % 60 + "分");

    }
}
