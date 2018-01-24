package com.naosim.dddwork.domain;

/**
 * 勤怠時間合計時間集計処理
 */
public interface WorkTimeTotalRepository {
    public WorkTimeTotalCalculation doWorktimeTaskExecute(WorkTimeTotalParam workTimeTotalParam);
}
