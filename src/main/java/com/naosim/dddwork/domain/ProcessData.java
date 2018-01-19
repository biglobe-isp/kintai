package com.naosim.dddwork.domain;

import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.IOException;

@EqualsAndHashCode
@ToString
public abstract class ProcessData {

    protected InputData inputData;

    public ProcessData(InputData inputData) {
        this.inputData = inputData;
    }

    public abstract void execute() throws IOException;
}
