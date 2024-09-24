package com.naosim.dddwork.domain;

/**
 * 勤務記録入力Repository
 */
interface InputWorkDataRepository {
    public DailyWorkDataList workDataList = new DailyWorkDataList();

    void writeDailyWorkData();
}