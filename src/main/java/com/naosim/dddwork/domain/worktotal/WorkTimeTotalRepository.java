package com.naosim.dddwork.domain.worktotal;

/**
 * 勤怠時間合計時間集計処理
 */
public interface WorkTimeTotalRepository {
    public WorkTimeTotal doWorktimeTaskExecute(WorkDateAndTimeTotal workDateAndTimeTotal);
}
