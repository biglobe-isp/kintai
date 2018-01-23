package com.naosim.dddwork.service;

import com.naosim.dddwork.datasource.WorkTimeInputRepositoryFile;
import com.naosim.dddwork.datasource.WorkTimeTotalRepositoryFile;
import com.naosim.dddwork.domain.WorkTimeInputForm;
import com.naosim.dddwork.domain.WorkTimeRepository;
import com.naosim.dddwork.domain.WorkTimeTotal;
import com.naosim.dddwork.domain.WorkTimeTotalForm;

public class WorkTimeService {
    WorkTimeRepository workTimeRepository;

    public void workTimeInput(WorkTimeInputForm workTimeInputForm) {
        //勤怠入力処理
        workTimeRepository = new WorkTimeInputRepositoryFile();
        workTimeRepository.doWorktimeTaskExecute(workTimeInputForm);
    }

    public WorkTimeTotal workTimeTotal(WorkTimeTotalForm workTimeTotalForm) {
        workTimeRepository = new WorkTimeTotalRepositoryFile();
        WorkTimeTotal workTimeTotal = (WorkTimeTotal) workTimeRepository.doWorktimeTaskExecute(workTimeTotalForm);

        return workTimeTotal;
    }
}
