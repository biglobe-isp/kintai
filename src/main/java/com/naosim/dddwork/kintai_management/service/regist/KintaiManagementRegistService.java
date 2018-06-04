package com.naosim.dddwork.kintai_management.service.regist;

import com.naosim.dddwork.kintai_management.domain.duty.regist.WorkingTimeDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 勤怠管理登録サービス
 */
@Service
public class KintaiManagementRegistService {

    @Autowired
    private WorkingTimeDataRepository workingTimeRegistrationRepository;

    public void kintaiManagementRegist(KintaiManagementRegistServiceInput kintaiManagementRegistServiceInput) {

        workingTimeRegistrationRepository.registWorkingTime(kintaiManagementRegistServiceInput.makeWorkingTimeRegistInput());
    }
}