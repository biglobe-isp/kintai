package jp.co.esumit.kintai.datasource.csv;

import jp.co.esumit.kintai.datasource.AppConst;

import java.io.FileWriter;
import java.io.IOException;

public class Writer {

    public void writer(CsvColumns columns){


        String date = columns.getTargetDay().getValue();
        String start = String.format("%02d%02d",columns.getStartTime().getStartHr().getValue(), columns.getStartTime().getStartMin().getValue());
        String end = String.format("%02d%02d",columns.getEndTime().getEndHr().getValue(), columns.getEndTime().getEndMin().getValue());
        String officeMins = String.valueOf(columns.getOfficeMinutes().getValue());
        String overtime = String.valueOf(columns.getOvertime().getValue());
        String now = columns.getRegisteredTime().getValue();

        try (FileWriter fw = new FileWriter(AppConst.FILE_NAME, true)){
            fw.write(String.format("%s,%s,%s,%s,%s,%s\n", date, start, end, officeMins, overtime, now));

        }catch(IOException e){
            e.printStackTrace();
        }

    }


}
