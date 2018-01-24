package com.naosim.dddwork.datasource;

import com.naosim.dddwork.domain.workdateandtime.WorkTimeCalculate;
import com.naosim.dddwork.domain.workdateandtime.WorkDateAndTime;
import com.naosim.dddwork.domain.workdateandtime.WorkTimeInputRepository;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class WorkTimeInputRepositoryFile implements WorkTimeInputRepository {
    @Override
    public void doWorktimeTaskExecute(WorkDateAndTime workTimeInputParam) {

        WorkTimeCalculate workTimeInput = new WorkTimeCalculate(workTimeInputParam);

        File file = new File("data.csv");
        try (FileWriter filewriter = new FileWriter(file, true)) {
            filewriter.write(String.format("%s,%s,%s,%s,%s,%s\n",
                    workTimeInputParam.getWorkDate().getValue(),
                    workTimeInputParam.getWorkTimeStart().getValue(),
                    workTimeInputParam.getWorkTimeEnd().getValue(),
                    workTimeInput.getNormalWorkTimeMinutes().getValue(),
                    workTimeInput.getOverWorkTimeMinutes().getValue(),
                    workTimeInputParam.getWorkTimeNow().getValue()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
