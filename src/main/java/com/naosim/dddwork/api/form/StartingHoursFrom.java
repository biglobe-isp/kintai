package com.naosim.dddwork.api.form;

import com.naosim.dddwork.domain.StartingHours;
import lombok.Getter;

public class StartingHoursFrom {
    @Getter
    private String value;

    public StartingHoursFrom (String startingHours) {
        this.value = startingHours;
    }

    public StartingHours getValueObject() {
        return new StartingHours(value);
    }
}
