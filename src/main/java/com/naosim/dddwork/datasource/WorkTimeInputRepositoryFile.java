package com.naosim.dddwork.datasource;

import com.naosim.dddwork.domain.WorkTimeInputForm;
import com.naosim.dddwork.domain.WorkTimeInput;
import com.naosim.dddwork.domain.WorkTimeRepository;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class WorkTimeInputRepositoryFile implements WorkTimeRepository<Void, WorkTimeInputForm> {
    @Override
    public Void doWorktimeTaskExecute(WorkTimeInputForm workTimeInputForm) {

        WorkTimeInput workTimeInputParam = new WorkTimeInput(workTimeInputForm);

        File file = new File("data.csv");
        try (FileWriter filewriter = new FileWriter(file, true)) {
            filewriter.write(String.format("%s,%s,%s,%s,%s,%s\n",
                    workTimeInputForm.getDate(),
                    workTimeInputForm.getStart(),
                    workTimeInputForm.getEnd(),
                    workTimeInputParam.getWorkMinutes(),
                    workTimeInputParam.getOverWorkMinutes(),
                    workTimeInputForm.getNow()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
