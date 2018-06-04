package com.naosim.dddwork.kintai_management.api.total;

import com.naosim.dddwork.kintai_management.api.form.TotalYearMonthForm;
import com.naosim.dddwork.kintai_management.service.total.KintaiManagementTotalServiceInput;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;
import javax.validation.constraints.NotNull;

/**
 * 勤怠管理集計リクエスト情報
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

    public void checkRequestArgs() {

        if(args.length < 1) {
            throw new RuntimeException("引数指定の誤り：引数が足りません");
        }

        this.totalYearMonthForm = new TotalYearMonthForm(args[0]);
    }

    public KintaiManagementTotalServiceInput makeKintaiManagementTotalServiceInput() {

        return new KintaiManagementTotalServiceInput(
                this.getTotalYearMonthForm().getValueObject()
        );
    }
}
