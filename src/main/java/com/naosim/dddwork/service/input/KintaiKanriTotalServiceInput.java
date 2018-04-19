package com.naosim.dddwork.service.input;

import com.naosim.dddwork.domain.word.MethodType;
import lombok.Getter;
import lombok.Setter;

/**
 * 勤務管理集計Service パラメータ
 */
public class KintaiKanriTotalServiceInput {

    /**
     * MethodType
     */
    @Getter
    @Setter
    private MethodType methodType;

    public KintaiKanriTotalServiceInput(MethodType methodType) {
        this.methodType = methodType;
    }
}
