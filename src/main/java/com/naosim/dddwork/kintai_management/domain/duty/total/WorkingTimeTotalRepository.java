package com.naosim.dddwork.kintai_management.domain.duty.total;

/**
 * 勤怠情報集計インタフェース
 */
public interface WorkingTimeTotalRepository {

        WorkingTimeTotalResult totalWorkingTime(WorkingTimeTotalInput workingTimeTotalInput);

        void registTotalWorkingTime(WorkingTimeTotalResult workingTimeTotalResult);
}
