package com.naosim.dddwork.kintai.datasource.workedtime.csv.command;

import com.naosim.dddwork.kintai.domain.model.timerecord.DailyWorkedTime;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


/**
 * ［指定日の勤怠登録］(CSVデータストア)
 */
public class DailyWorkedTimeRegistration {

    public void save(DailyWorkedTime dailyWorkedTime) {

        File file = new File("data.csv");

        try(FileWriter writer = new FileWriter(file, true)) {

            writer.write(String.format("%s,%s,%s,%s,%s,%s\n",
                    dailyWorkedTime.getAttendanceDate(),
                    dailyWorkedTime.getBeginTime(),
                    dailyWorkedTime.getEndTime(),
                    dailyWorkedTime.getWorkMinutes(),
                    dailyWorkedTime.getOverWorkMinutes(),
                    dailyWorkedTime.getNow()));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
