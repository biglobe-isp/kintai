package com.naosim.dddwork.service;

import com.naosim.dddwork.datasource.WorkTimeInputRepositoryFile;
import com.naosim.dddwork.datasource.WorkTimeTotalRepositoryFile;
import com.naosim.dddwork.domain.WorkTimeInputParam;
import com.naosim.dddwork.domain.WorkTimeInputRepository;
import com.naosim.dddwork.domain.WorkTimeTotal;
import com.naosim.dddwork.domain.WorkTimeTotalCalculation;
import com.naosim.dddwork.domain.WorkTimeTotalParam;
import com.naosim.dddwork.domain.WorkTimeTotalRepository;

public class WorkTimeService {
    //WorkTimeRepository workTimeRepository;

    private WorkTimeInputRepository workTimeInputRepository;
    private WorkTimeTotalRepository workTimeTotalRepository;


    /**
     * 勤怠入力処理
     *
     * @param workTimeInputParam
     */
    public void workTimeInput(WorkTimeInputParam workTimeInputParam) {
        workTimeInputRepository = new WorkTimeInputRepositoryFile();
        workTimeInputRepository.doWorktimeTaskExecute(workTimeInputParam);
    }

    /**
     * 勤怠合計時間表示
     *
     * @param workTimeTotalParam
     * @return
     */
    public WorkTimeTotal workTimeTotal(WorkTimeTotalParam workTimeTotalParam) {
        workTimeTotalRepository = new WorkTimeTotalRepositoryFile();
        WorkTimeTotalCalculation workTimeTotalCalculation = workTimeTotalRepository.doWorktimeTaskExecute(workTimeTotalParam);

        WorkTimeTotal workTimeTotal = new WorkTimeTotal(workTimeTotalCalculation.getTotalWorkMinutesMap(), workTimeTotalCalculation.getTotalOverWorkMinutesMap());

        return workTimeTotal;
    }
}
