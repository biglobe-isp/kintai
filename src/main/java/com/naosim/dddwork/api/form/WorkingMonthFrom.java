package com.naosim.dddwork.api.form;

import com.naosim.dddwork.domain.WorkingMonth;
import jp.co.biglobe.lib.publication.form.FormToValueObject;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;

@ToString(includeFieldNames = false)
@EqualsAndHashCode(callSuper = false)
public class WorkingMonthFrom implements FormToValueObject<WorkingMonth> {
    @Getter
    @NotNull
    private String value;

    public WorkingMonthFrom(String workingMonth) {
        if (workingMonth == null || workingMonth.isEmpty()) {
            throw new RuntimeException("引数[勤務月]が足りません");
        }

        this.value = workingMonth;
    }

    @Override
    public WorkingMonth getValueObject() {

        return new  WorkingMonth(YearMonth.parse(this.value, DateTimeFormatter.ofPattern("yyyyMM")));
    }
}
