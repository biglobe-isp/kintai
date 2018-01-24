package com.naosim.dddwork.service;

import com.naosim.dddwork.datasource.WorkTimeInputRepositoryFile;
import com.naosim.dddwork.datasource.WorkTimeTotalRepositoryFile;
import com.naosim.dddwork.domain.workdateandtime.WorkDateAndTime;
import com.naosim.dddwork.domain.workdateandtime.WorkTimeInputRepository;
import com.naosim.dddwork.domain.worktotal.WorkTimeTotalcalculation;
import com.naosim.dddwork.domain.worktotal.WorkTimeTotal;
import com.naosim.dddwork.domain.worktotal.WorkTimeTotalRepository;
import com.naosim.dddwork.domain.worktotal.WorkDateAndTimeTotal;

public class WorkTimeService {
    //WorkTimeRepository workTimeRepository;

    private WorkTimeInputRepository workTimeInputRepository;
    private WorkTimeTotalRepository workTimeTotalRepository;


    /**
     * 勤怠入力処理
     *
     * @param workDateAndTime
     */
    public void workTimeInput(WorkDateAndTime workDateAndTime) {
        workTimeInputRepository = new WorkTimeInputRepositoryFile();
        workTimeInputRepository.doWorktimeTaskExecute(workDateAndTime);
    }

    /**
     * 勤怠合計時間表示
     *
     * @param workDateAndTimeTotal
     * @return
     */
    public WorkTimeTotalcalculation workTimeTotal(WorkDateAndTimeTotal workDateAndTimeTotal) {
        workTimeTotalRepository = new WorkTimeTotalRepositoryFile();
        WorkTimeTotal workTimeTotalCalculation = workTimeTotalRepository.doWorktimeTaskExecute(workDateAndTimeTotal);

        WorkTimeTotalcalculation workTimeTotal = new WorkTimeTotalcalculation(workTimeTotalCalculation.getTotalNormalWorkMinutes(), workTimeTotalCalculation.getTotalOverWorkMinutes());

        return workTimeTotal;
    }
}
