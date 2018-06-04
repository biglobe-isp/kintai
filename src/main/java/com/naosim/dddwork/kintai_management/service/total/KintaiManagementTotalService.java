package com.naosim.dddwork.kintai_management.service.total;

import com.naosim.dddwork.kintai_management.domain.duty.regist.WorkingTimeDataRepository;
import com.naosim.dddwork.kintai_management.domain.duty.total.WorkingTimeTotalDataRepository;
import com.naosim.dddwork.kintai_management.domain.duty.total.WorkingTimeTotalDataResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 勤怠管理集計サービス
 */
@Service
public class KintaiManagementTotalService {

    @Autowired
    private WorkingTimeTotalDataRepository workingTimeTotalDataRepository;

    @Autowired
    private WorkingTimeDataRepository workingTimeDateRepository;

    public void kintaiManagementTotal(KintaiManagementTotalServiceInput kintaiManagementTotalServiceInput) {

        WorkingTimeTotalDataResult workingTimeTotalResult =
                workingTimeDateRepository.getTotalWorkingTime(kintaiManagementTotalServiceInput.makeWorkingTimeTotalInput());

        workingTimeTotalDataRepository.registTotalWorkingTime(workingTimeTotalResult);
    }
}