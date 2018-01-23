package com.naosim.dddwork.service;

import com.naosim.dddwork.datasource.WorkTimeInputRepositoryFile;
import com.naosim.dddwork.datasource.WorkTimeTotalRepositoryFile;
import com.naosim.dddwork.api.form.WorkTimeInputForm;
import com.naosim.dddwork.domain.WorkTimeInputParam;
import com.naosim.dddwork.domain.WorkTimeRepository;
import com.naosim.dddwork.domain.WorkTimeTotal;
import com.naosim.dddwork.api.form.WorkTimeTotalForm;
import com.naosim.dddwork.domain.WorkTimeTotalParam;

public class WorkTimeService {
    WorkTimeRepository workTimeRepository;

    /**
     * 勤怠入力処理
     *
     * @param workTimeInputParam
     */
    public void workTimeInput(WorkTimeInputParam workTimeInputParam) {
        workTimeRepository = new WorkTimeInputRepositoryFile();
        workTimeRepository.doWorktimeTaskExecute(workTimeInputParam);
    }

    /**
     * 勤怠合計時間表示
     *
     * @param workTimeTotalParam
     * @return
     */
    public WorkTimeTotal workTimeTotal(WorkTimeTotalParam workTimeTotalParam) {
        workTimeRepository = new WorkTimeTotalRepositoryFile();
        WorkTimeTotal workTimeTotal = (WorkTimeTotal) workTimeRepository.doWorktimeTaskExecute(workTimeTotalParam);

        return workTimeTotal;
    }
}
