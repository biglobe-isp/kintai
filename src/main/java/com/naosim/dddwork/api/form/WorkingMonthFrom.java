package com.naosim.dddwork.api.form;

import com.naosim.dddwork.domain.WorkingMonth;
import lombok.Getter;

public class WorkingMonthFrom {
    @Getter
    private String value;

    public WorkingMonthFrom (String workingMonth) {
        this.value = workingMonth;
    }

    public WorkingMonth getValueObject() {
        return new WorkingMonth(value);
    }
}
