package com.naosim.dddwork.service;

import com.naosim.dddwork.datasource.LocalCSVWorkData;
import com.naosim.dddwork.domain.WorkDataRepository;
import com.naosim.dddwork.domain.daily_work.DailyWorkData;

/**
 * 勤務記録入力機能
 */
public class InputFunction {
    public static DailyWorkData execute(InputInformation information) {
        WorkDataRepository inputWorkDataRepository = new LocalCSVWorkData();

        return inputWorkDataRepository.writeDailyWorkData(
                information.getWorkDate(),
                information.getStartWorkTime(),
                information.getEndWorkTime());
    }
}