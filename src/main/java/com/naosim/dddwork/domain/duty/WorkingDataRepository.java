package com.naosim.dddwork.domain.duty;

import java.util.ArrayList;

/**
 * 勤怠管理API　勤務データ読み書きRepository
 */
public interface WorkingDataRepository {

    void write(WorkingData workingData) throws Exception;

    ArrayList<WorkingData> read() throws Exception;

    WorkingSummaryData summary(ArrayList<WorkingData> workingDataList);
}
