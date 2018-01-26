package com.naosim.dddwork.service;

import com.naosim.dddwork.datasource.WorkTimeInputRepositoryFile;
import com.naosim.dddwork.datasource.WorkTimeTotalRepositoryFile;
import com.naosim.dddwork.domain.workdateandtime.WorkDateAndTime;
import com.naosim.dddwork.domain.workdateandtime.WorkTimeInputRepository;
import com.naosim.dddwork.domain.workdateandtime.WorkTimeMinutes;
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
        WorkTimeMinutes workTimeMinutes = new WorkTimeMinutes(workDateAndTime);
        workTimeInputRepository = new WorkTimeInputRepositoryFile();
        workTimeInputRepository.registerWork_time(workDateAndTime, workTimeMinutes);
    }

    /**
     * 勤怠合計時間表示
     *
     * @param workDateAndTimeTotal
     * @return
     */
    public WorkTimeTotalcalculation workTimeTotal(WorkDateAndTimeTotal workDateAndTimeTotal) {
        workTimeTotalRepository = new WorkTimeTotalRepositoryFile();
        WorkTimeTotal workTimeTotal = workTimeTotalRepository.doWorktimeTaskExecute(workDateAndTimeTotal);

        WorkTimeTotalcalculation workTimeTotalcalculation = new WorkTimeTotalcalculation(workTimeTotal.getTotalNormalWorkMinutes(), workTimeTotal.getTotalOverWorkMinutes());

        return workTimeTotalcalculation;
    }
}
