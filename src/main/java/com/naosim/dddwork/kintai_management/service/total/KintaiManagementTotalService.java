package com.naosim.dddwork.kintai_management.service.total;

import com.naosim.dddwork.kintai_management.domain.duty.total.WorkingTimeTotalRepository;
import com.naosim.dddwork.kintai_management.domain.duty.total.WorkingTimeTotalResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 勤怠管理集計サービス
 */
@Service
public class KintaiManagementTotalService {

    @Autowired
    private WorkingTimeTotalRepository workingTimeTotalRepository;

    public void kintaiManagementTotal(KintaiManagementTotalServiceInput kintaiManagementTotalServiceInput) {

        WorkingTimeTotalResult workingTimeTotalResult =
                workingTimeTotalRepository.totalWorkingTime(kintaiManagementTotalServiceInput.makeWorkingTimeTotalInput());

        workingTimeTotalRepository.registTotalWorkingTime(workingTimeTotalResult);
    }
}