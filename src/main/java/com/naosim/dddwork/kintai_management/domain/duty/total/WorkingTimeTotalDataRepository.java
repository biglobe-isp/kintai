package com.naosim.dddwork.kintai_management.domain.duty.total;

/**
 * 勤怠集計情報リポジトリインタフェース
 */
public interface WorkingTimeTotalDataRepository {

        void registTotalWorkingTime(WorkingTimeTotalDataResult workingTimeTotalResult);
}
