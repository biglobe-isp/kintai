package com.naosim.dddwork.datasource;

import com.naosim.dddwork.domain.DailyWorkDataList;
import com.naosim.dddwork.domain.WorkDataRepository;
import com.naosim.dddwork.domain.daily_work.DailyWorkData;
import com.naosim.dddwork.domain.daily_work.EndWorkTime;
import com.naosim.dddwork.domain.daily_work.StartWorkTime;
import com.naosim.dddwork.domain.daily_work.WorkDate;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

/**
 * ローカルCSVファイルから勤務記録入出力
 */
public class LocalCSVWorkData implements WorkDataRepository {
    @Override
    public String writeDailyWorkData(
            WorkDate workDate,
            StartWorkTime startWorkTime,
            EndWorkTime endWorkTime) {
        File localFile = new File(new WorkDataFilePath().getFilePath());

        DailyWorkData writeData = new DailyWorkData(workDate, startWorkTime, endWorkTime);

        String inputData = String.format(
                "%s, %s, %s\n",
                writeData.getWorkDate().getValue(),
                writeData.getTotalWorkTimes().getValue(),
                LocalDateTime.now()
        );

        try (FileWriter filewriter = new FileWriter(localFile, true)) {
            filewriter.write(inputData);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return inputData;
    }

    @Override
    public DailyWorkDataList fetchDailyWorkData() {
        return null;
    }
}