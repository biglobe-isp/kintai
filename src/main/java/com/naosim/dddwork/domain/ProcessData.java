package com.naosim.dddwork.domain;

import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode
@ToString
public abstract class ProcessData {

    protected InputData inputData;

    public ProcessData(InputData inputData) {
        this.inputData = inputData;
        if (!this.isCorrectMethodType())
            throw new RuntimeException("指定されたメソッドタイプが異なるため、インスタンス化できません");

    }

    protected abstract boolean isCorrectMethodType();
}
