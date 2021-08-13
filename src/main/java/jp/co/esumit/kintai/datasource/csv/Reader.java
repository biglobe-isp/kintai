package jp.co.esumit.kintai.datasource.csv;

import jp.co.esumit.kintai.datasource.AppConst;
import jp.co.esumit.kintai.domain.field.EndTime;
import jp.co.esumit.kintai.domain.field.OfficeMinutes;
import jp.co.esumit.kintai.domain.field.Overtime;
import jp.co.esumit.kintai.domain.field.RegisteredTime;
import jp.co.esumit.kintai.domain.field.StartTime;
import jp.co.esumit.kintai.domain.field.TargetDay;
import jp.co.esumit.kintai.domain.field.TargetYearMonth;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class Reader {

    public List<CsvColumns> reader(TargetYearMonth targetYearMonth){

        List<CsvColumns> targetColumn = new ArrayList<>();

        try (FileReader fr = new FileReader(new File(AppConst.FILE_NAME));
             BufferedReader br = new BufferedReader(fr)){

            String line = br.readLine();
            while(line != null){
                String[] columns = line.split(",");
                if(!columns[0].startsWith(targetYearMonth.getValue())) {

                    line = br.readLine();
                    continue;
                }

                CsvColumns csvColumns = new CsvColumns(
                        new TargetDay(columns[0]),
                        new StartTime(columns[1].replace(":","")),
                        new EndTime(columns[2].replace(":","")),
                        new OfficeMinutes(Integer.parseInt(columns[3])),
                        new Overtime(Integer.parseInt(columns[4])),
                        new RegisteredTime(columns[5])
                );

                targetColumn.add(csvColumns);
                line = br.readLine();
            }

        }catch(Exception e){
            e.printStackTrace();
        }

        return targetColumn;
    }

}
