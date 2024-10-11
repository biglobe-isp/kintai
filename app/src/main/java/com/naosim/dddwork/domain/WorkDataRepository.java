package com.naosim.dddwork.domain;

import com.naosim.dddwork.domain.daily_work.EndWorkTime;
import com.naosim.dddwork.domain.daily_work.StartWorkTime;
import com.naosim.dddwork.domain.daily_work.WorkDate;

/**
 * 勤務記録取得Repository
 */
public interface WorkDataRepository {
    String writeDailyWorkData(WorkDate workDate, StartWorkTime startWorkTime, EndWorkTime endWorkTime);
    DailyWorkDataList fetchDailyWorkData();
}