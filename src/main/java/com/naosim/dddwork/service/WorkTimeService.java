package com.naosim.dddwork.service;

import com.naosim.dddwork.datasource.WorkTimeInputRepositoryFile;
import com.naosim.dddwork.datasource.WorkTimeTotalRepositoryFile;
import com.naosim.dddwork.domain.WorkTimeInputForm;
import com.naosim.dddwork.domain.WorkTimeRepository;
import com.naosim.dddwork.domain.WorkTimeTotal;
import com.naosim.dddwork.domain.WorkTimeTotalForm;

public class WorkTimeService {
    WorkTimeRepository workTimeRepository;

    /**
     * 勤怠入力処理
     *
     * @param workTimeInputForm
     */
    public void workTimeInput(WorkTimeInputForm workTimeInputForm) {
        workTimeRepository = new WorkTimeInputRepositoryFile();
        workTimeRepository.doWorktimeTaskExecute(workTimeInputForm);
    }

    /**
     * 勤怠合計時間表示
     *
     * @param workTimeTotalForm
     * @return
     */
    public WorkTimeTotal workTimeTotal(WorkTimeTotalForm workTimeTotalForm) {
        workTimeRepository = new WorkTimeTotalRepositoryFile();
        WorkTimeTotal workTimeTotal = (WorkTimeTotal) workTimeRepository.doWorktimeTaskExecute(workTimeTotalForm);

        return workTimeTotal;
    }
}
