package com.naosim.dddwork.kintai_management.service.total;

import com.naosim.dddwork.kintai_management.domain.duty.total.WorkingTimeTotalRepository;
import com.naosim.dddwork.kintai_management.domain.duty.total.WorkingTimeTotalResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * 勤怠管理集計サービス
 */
@Service
public class KintaiManagementTotalService {

    @Autowired
    private WorkingTimeTotalRepository workingTimeTotalRepository;

    public void kintaiManagementTotal(KintaiManagementTotalInput kintaiManagementTotalInput) {

        Optional<WorkingTimeTotalResult> workingTimeTotalResultOptional =
                workingTimeTotalRepository.totalWorkingTime(kintaiManagementTotalInput.makeWorkingTimeTotalInput());

        WorkingTimeTotalResult workingTimeTotalResult = workingTimeTotalResultOptional.orElseThrow(()
                -> new IllegalStateException("を取得できませんでした"));

        System.out.println("勤務時間: " + workingTimeTotalResult.getTotalWorkingTime().getValue() / 60 + "時間" + workingTimeTotalResult.getTotalWorkingTime().getValue() % 60 + "分");
        System.out.println("残業時間: " + workingTimeTotalResult.getTotalOverWorkingTime().getValue() / 60 + "時間" + workingTimeTotalResult.getTotalOverWorkingTime().getValue() % 60 + "分");

    }
}