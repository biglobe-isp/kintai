package com.naosim.dddwork.kintai.datasource.workedtime.csv.command;

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

        File file = new File("data.csv");

        try(FileWriter writer = new FileWriter(file, true)) {

            writer.write(String.format("%s,%s,%s,%s,%s,%s\n",
                    dailyWorkedTime.getAttendanceDate().storedValue(),
                    dailyWorkedTime.getBeginTime().storedValue(),
                    dailyWorkedTime.getEndTime().storedValue(),
                    dailyWorkedTime.getWorkMinutes().getValue(),        //TODO: どうする？
                    dailyWorkedTime.getWorkedTimeAsOvertime().storedValue(),
                    dailyWorkedTime.getRecordTimestamp().storedValue()));
        }
        catch (IOException e) {
            throw new SystemException("[指定日の勤怠登録]処理中に入出力例外が発生しました．", e);
        }
    }
}
