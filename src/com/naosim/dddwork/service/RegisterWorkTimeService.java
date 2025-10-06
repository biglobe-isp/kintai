package com.naosim.dddwork.service;

import com.naosim.dddwork.domain.StoreWorkTime;
import com.naosim.dddwork.domain.WorkDataRepository;
import com.naosim.dddwork.domain.WorkTime;

public class RegisterWorkTimeService {
    private final WorkDataRepository workDataRepository;

    public RegisterWorkTimeService(WorkDataRepository workDataRepository) {
        this.workDataRepository = workDataRepository;
    }

    public void registerWorkTime(WorkTime workTime) {
        int workMinutes = workTime.calculateWorkTimeMinutes();
        int overWorkMinutes = workTime.calculateOverWorkTimeMinutes();
        StoreWorkTime dailyWorkData = new StoreWorkTime(workMinutes, overWorkMinutes);
        workDataRepository.save(workTime, dailyWorkData);
    }
}
//api層でデータソースを受け取るため、依存にならない。（インスタンスに設定）
