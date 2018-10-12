package com.naosim.dddwork.api;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@ToString(includeFieldNames = false)
@EqualsAndHashCode
@AllArgsConstructor
public class ProcessingDivision {
    @Getter
    private final String value;

    public boolean isInput() {

        return this.value.equals("input");
    }

    public boolean isTotal() {
        return this.value.equals("total");

    }

}
