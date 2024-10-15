package com.naosim.dddwork.service;

import com.naosim.dddwork.datasource.LocalCSVWorkData;
import com.naosim.dddwork.domain.*;
import com.naosim.dddwork.domain.daily_work.AggregationYearMonth;

/**
 * 勤務記録集計機能
 */
public class TotalFunction {
    public static AggregationResult execute(InputInformation information) {
        WorkDataRepository fetchWorkDataRepository = new LocalCSVWorkData();

        DailyWorkDataList workDataList = getSearchedWorkDataList(fetchWorkDataRepository, information.getAggregationYearMonth());

        return aggregate(workDataList);
    }

    private static DailyWorkDataList getSearchedWorkDataList(WorkDataRepository repository, AggregationYearMonth aggregationYearMonth) {
        return repository.fetchDailyWorkData(aggregationYearMonth);
    }

    private static AggregationResult aggregate(DailyWorkDataList workDataList) {
        MonthlyWorkingMinutes workingMinutes = new MonthlyWorkingMinutes(workDataList);
        MonthlyOverworkingMinutes overworkingMinutes = new MonthlyOverworkingMinutes(workDataList, workingMinutes);
        MonthlyNormalWorkingMinutes normalWorkingMinutes = new MonthlyNormalWorkingMinutes(workingMinutes, overworkingMinutes);

        return new AggregationResult(overworkingMinutes,normalWorkingMinutes);
    }
}

