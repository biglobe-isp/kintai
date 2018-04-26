package com.naosim.dddwork.kintai_management.api.total;

import com.naosim.dddwork.kintai_management.api.form.*;
import com.naosim.dddwork.kintai_management.service.total.KintaiManagementTotalInput;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

/**
 * 勤怠管理集計リクエスト
 */
@Component
public class KintaiManagementTotalRequest {

    @Getter
    @Setter
    @NotNull
    private String[] args;

    @Getter
    @Setter
    @NotNull
    private TotalYearMonthForm totalYearMonthForm;

    public KintaiManagementTotalInput makeKintaiManagementTotalInput() {
        return new KintaiManagementTotalInput(
                this.getTotalYearMonthForm().getValueObject()
        );
    }

    public KintaiManagementTotalInput makeKintaiManagementTotalInputArgs() {

        this.totalYearMonthForm = new TotalYearMonthForm(args[0]);

        return new KintaiManagementTotalInput(
                this.getTotalYearMonthForm().getValueObject()
        );
    }


}
