package com.naosim.dddwork.domain;

public interface FetchWorkDataRepository {
    DailyWorkDataList fetchDailyWorkData(DataPath filePath);
}