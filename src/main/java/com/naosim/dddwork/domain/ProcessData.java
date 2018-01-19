package com.naosim.dddwork.domain;

import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode
@ToString
public abstract class ProcessData {

    protected InputData inputData;

    public ProcessData(InputData inputData) {
        this.inputData = inputData;
    }
}
