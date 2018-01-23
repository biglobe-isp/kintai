package com.naosim.dddwork.api.form;

import com.naosim.dddwork.domain.WorkTimeTotalParam;
import jp.co.biglobe.lib.publication.form.FormToValueObject;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 合計時間計算入力オブジェクト
 */
@RequiredArgsConstructor
public class WorkTimeTotalForm implements FormToValueObject<WorkTimeTotalParam> {

    @Getter
    private final String yearMonth;

    @Override
    public WorkTimeTotalParam getValueObject() {
        return new WorkTimeTotalParam(yearMonth);
    }
}
