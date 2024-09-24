package com.naosim.dddwork.domain;

/**
 * 勤務記録取得Repository
 */
interface FetchWorkDataRepository {
    public DailyWorkDataList workDataList = new DailyWorkDataList();

    DailyWorkDataList fetchDailyWorkData();
}