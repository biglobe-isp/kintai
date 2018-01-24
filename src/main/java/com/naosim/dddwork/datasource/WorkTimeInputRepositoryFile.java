package com.naosim.dddwork.datasource;

import com.naosim.dddwork.domain.workdateandtime.WorkTimeCalculate;
import com.naosim.dddwork.domain.workdateandtime.WorkDateAndTime;
import com.naosim.dddwork.domain.workdateandtime.WorkTimeInputRepository;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class WorkTimeInputRepositoryFile implements WorkTimeInputRepository {
    @Override
    public void doWorktimeTaskExecute(WorkDateAndTime workDateAndTime) {

        WorkTimeCalculate workTimeCalculate = new WorkTimeCalculate(workDateAndTime);

        File file = new File("data.csv");
        try (FileWriter filewriter = new FileWriter(file, true)) {
            filewriter.write(String.format("%s,%s,%s,%s,%s,%s\n",
                    workDateAndTime.getWorkDate().getValue(),
                    workDateAndTime.getWorkTimeStart().getValue(),
                    workDateAndTime.getWorkTimeEnd().getValue(),
                    workTimeCalculate.getNormalWorkTimeMinutes().getValue(),
                    workTimeCalculate.getOverWorkTimeMinutes().getValue(),
                    workDateAndTime.getWorkTimeNow().getValue()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
