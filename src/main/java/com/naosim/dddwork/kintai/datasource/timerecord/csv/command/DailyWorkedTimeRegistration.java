package com.naosim.dddwork.kintai.datasource.timerecord.csv.command;

import com.naosim.dddwork.kintai.datasource.settings.DataSourceConfiguration;
import com.naosim.dddwork.kintai.domain.model.timerecord.DailyWorkedTime;
import com.naosim.dddwork.kintai.shared.exception.SystemException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


/**
 * ［指定日の勤怠登録］(CSVデータストア)
 */
public class DailyWorkedTimeRegistration {

    public void save(DailyWorkedTime dailyWorkedTime) {

        File file = new File(DataSourceConfiguration.CSV_FILE_NAME);

        try(FileWriter writer = new FileWriter(file, true)) {

            writer.write(String.format("%s,%s,%s,%s,%s,%s\n",
                    dailyWorkedTime.getDailyTimeRecord().getAttendanceDate().storedValue(),
                    dailyWorkedTime.getDailyTimeRecord().getSpentTimeRange().beginTime().storedValue(),
                    dailyWorkedTime.getDailyTimeRecord().getSpentTimeRange().endTime().storedValue(),
                    dailyWorkedTime.getPaidWorkedTime().storedValue(),
                    dailyWorkedTime.getPaidWorkedTime().workedTimeAsOvertime().storedValue(),
                    dailyWorkedTime.getRecordTimestamp().storedValue()));
        }
        catch (IOException e) {
            throw new SystemException("[指定日の勤怠登録]処理中に入出力例外が発生しました．", e);
        }
    }
}
