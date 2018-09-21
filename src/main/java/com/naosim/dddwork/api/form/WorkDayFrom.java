package com.naosim.dddwork.api.form;

import com.naosim.dddwork.domain.WorkDay;
import lombok.Getter;

public class WorkDayFrom {
    @Getter
    private String value;

    public WorkDayFrom (String workDay) {
        this.value = workDay;
    }

    public WorkDay getValueObject() {
        return new WorkDay(value);
    }
}
