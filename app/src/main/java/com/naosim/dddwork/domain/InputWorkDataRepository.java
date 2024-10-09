package com.naosim.dddwork.domain;

import com.naosim.dddwork.domain.daily_work.DailyWorkData;

/**
 * 勤務記録入力Repository
 */
public interface InputWorkDataRepository {
    String writeDailyWorkData(DailyWorkData dailyWorkData);
}