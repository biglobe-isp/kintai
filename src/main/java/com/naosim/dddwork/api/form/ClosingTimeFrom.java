package com.naosim.dddwork.api.form;

import com.naosim.dddwork.domain.ClosingTime;
import lombok.Getter;

public class ClosingTimeFrom {
    @Getter
    private String value;

    public ClosingTimeFrom (String closingTime) {
        this.value = closingTime;
    }

    public ClosingTime getValueObject() {
        return new ClosingTime(value);
    }
}
