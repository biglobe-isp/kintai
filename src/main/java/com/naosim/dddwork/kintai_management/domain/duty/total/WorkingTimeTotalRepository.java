package com.naosim.dddwork.kintai_management.domain.duty.total;

/**
 *
 */
public interface WorkingTimeTotalRepository {

        WorkingTimeTotalResult totalWorkingTime(WorkingTimeTotalInput workingTimeTotalInput);

        void registTotalWorkingTime(WorkingTimeTotalResult workingTimeTotalResult);

}
