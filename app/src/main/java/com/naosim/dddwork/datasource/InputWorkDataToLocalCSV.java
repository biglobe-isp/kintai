package com.naosim.dddwork.datasource;

import com.naosim.dddwork.domain.InputWorkDataRepository;
import com.naosim.dddwork.domain.daily_work.DailyWorkData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

public class InputWorkDataToLocalCSV implements InputWorkDataRepository {
    public String writeDailyWorkData(DailyWorkData dailyWorkData){
            File localFile = new File(new WorkDataFilePath().getFilePath());

        String inputData = String.format(
                "%s,%s,%s\n",
                dailyWorkData.getWorkDate(),
                dailyWorkData.getTotalWorkTimes(),
                LocalDateTime.now()
        );

        try (FileWriter filewriter = new FileWriter(localFile, true)) {
            filewriter.write(inputData);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return inputData;
    }
}