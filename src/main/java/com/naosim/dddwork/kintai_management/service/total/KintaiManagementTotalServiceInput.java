package com.naosim.dddwork.kintai_management.service.total;

import com.naosim.dddwork.kintai_management.domain.duty.total.WorkingTimeTotalDataInput;
import com.naosim.dddwork.kintai_management.domain.word.TotalYearMonth;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;

/**
 * 勤怠管理集計サービス入力情報
 */
@AllArgsConstructor
@Getter
public class KintaiManagementTotalServiceInput {

    @NonNull
    private TotalYearMonth totalYearMonth;

    public WorkingTimeTotalDataInput makeWorkingTimeTotalInput() {
        return new WorkingTimeTotalDataInput(
                totalYearMonth
                );
    }
}