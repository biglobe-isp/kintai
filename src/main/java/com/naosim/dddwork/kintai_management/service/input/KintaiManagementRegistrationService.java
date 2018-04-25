package com.naosim.dddwork.kintai_management.service.input;

import com.naosim.dddwork.kintai_management.domain.duty.input.WorkingTimeRegistrationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 勤怠管理登録サービス
 */
@Service
public class KintaiManagementRegistrationService {

    @Autowired
    private WorkingTimeRegistrationRepository workingTimeRegistrationRepository;

    public void kintaiManagementRegistration(KintaiManagementRegistrationInput kintaiManagementRegistrationInput) {

        workingTimeRegistrationRepository.registWorkingTime(kintaiManagementRegistrationInput.makeWorkingTimeRegistrationInput());

    }
}