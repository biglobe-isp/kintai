package com.naosim.dddwork.service;

import com.naosim.dddwork.domain.WorkDataRepository;
import com.naosim.dddwork.domain.WorkTime;
import com.naosim.dddwork.domain.WorkTimeStorage;

public class WorkTimeRegisterService {
    private final WorkDataRepository workDataRepository;

    public WorkTimeRegisterService(WorkDataRepository workDataRepository) {
        this.workDataRepository = workDataRepository;
    }

    public void registerWorkTime(WorkTime workTime) {
        int workMinutes = workTime.calculateWorkTimeMinutes();
        int overWorkMinutes = workTime.calculateOverWorkTimeMinutes();
        WorkTimeStorage dailyWorkData = new WorkTimeStorage(workMinutes, overWorkMinutes);
        workDataRepository.save(workTime, dailyWorkData);
    }
}
//api層でデータソースを受け取るため、依存にならない。（インスタンスに設定）
