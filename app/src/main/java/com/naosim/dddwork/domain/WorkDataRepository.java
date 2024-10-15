package com.naosim.dddwork.domain;

import com.naosim.dddwork.domain.daily_work.*;

/**
 * 勤務記録取得Repository
 */
public interface WorkDataRepository {
    DailyWorkData writeDailyWorkData(WorkDate workDate, StartWorkTime startWorkTime, EndWorkTime endWorkTime);
    DailyWorkDataList fetchDailyWorkData(AggregationYearMonth aggregationYearMonth);
}