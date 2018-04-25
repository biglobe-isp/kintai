package com.naosim.dddwork.kintai_management.api.total;


import com.naosim.dddwork.kintai_management.api.form.*;
import com.naosim.dddwork.kintai_management.service.total.KintaiManagementTotalInput;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * 勤怠管理集計リクエスト
 */
@Component
public class KintaiManagementTotalRequest {

    @Getter
    @Setter
    @NotNull
    @Valid
    private TotalYearMonthForm totalYearMonthForm;

    public KintaiManagementTotalInput makeKintaiManagementTotalInput() {
        return new KintaiManagementTotalInput(
                this.getTotalYearMonthForm().getValueObject()
        );
    }
}
