package com.naosim.dddwork.domain;

import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode
@ToString
public abstract class ProcessData {

    protected InputKintai inputKintai;

    public ProcessData(InputKintai inputKintai) {
        this.inputKintai = inputKintai;
        if (!this.isCorrectMethodType())
            throw new RuntimeException("指定されたメソッドタイプが異なるため、インスタンス化できません");

    }

    protected abstract boolean isCorrectMethodType();
}
