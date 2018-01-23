package com.naosim.dddwork.datasource;

import com.naosim.dddwork.api.form.WorkTimeInputForm;
import com.naosim.dddwork.domain.WorkTimeInput;
import com.naosim.dddwork.domain.WorkTimeInputParam;
import com.naosim.dddwork.domain.WorkTimeRepository;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class WorkTimeInputRepositoryFile implements WorkTimeRepository<Void, WorkTimeInputParam> {
    @Override
    public Void doWorktimeTaskExecute(WorkTimeInputParam workTimeInputParam) {

        WorkTimeInput workTimeInput = new WorkTimeInput(workTimeInputParam);

        File file = new File("data.csv");
        try (FileWriter filewriter = new FileWriter(file, true)) {
            filewriter.write(String.format("%s,%s,%s,%s,%s,%s\n",
                    workTimeInputParam.getDate(),
                    workTimeInputParam.getStart(),
                    workTimeInputParam.getEnd(),
                    workTimeInput.getWorkMinutes(),
                    workTimeInput.getOverWorkMinutes(),
                    workTimeInputParam.getNow()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
