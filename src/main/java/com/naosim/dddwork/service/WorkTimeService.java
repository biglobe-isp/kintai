package com.naosim.dddwork.service;

import com.naosim.dddwork.datasource.WorkTimeInputRepositoryFile;
import com.naosim.dddwork.datasource.WorkTimeTotalRepositoryFile;
import com.naosim.dddwork.domain.workdateandtime.WorkDateAndTime;
import com.naosim.dddwork.domain.workdateandtime.WorkTimeInputRepository;
import com.naosim.dddwork.domain.worktotal.WorkTimeTotal;
import com.naosim.dddwork.domain.worktotal.WorkTimeTotalCalculation;
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
    public WorkTimeTotal workTimeTotal(WorkDateAndTimeTotal workDateAndTimeTotal) {
        workTimeTotalRepository = new WorkTimeTotalRepositoryFile();
        WorkTimeTotalCalculation workTimeTotalCalculation = workTimeTotalRepository.doWorktimeTaskExecute(workDateAndTimeTotal);

        WorkTimeTotal workTimeTotal = new WorkTimeTotal(workTimeTotalCalculation.getTotalNormalWorkMinutes(), workTimeTotalCalculation.getTotalOverWorkMinutes());

        return workTimeTotal;
    }
}
