package com.naosim.dddwork.api.form;

import com.naosim.dddwork.domain.workdateandtime.WorkDate;
import com.naosim.dddwork.domain.worktotal.WorkDateAndTimeTotal;
import com.naosim.dddwork.domain.worktotal.WorkTotalYeaAndMonth;
import jp.co.biglobe.lib.publication.form.FormToValueObject;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 合計時間計算入力オブジェクト
 */
@RequiredArgsConstructor
public class WorkTimeTotalForm implements FormToValueObject<WorkDateAndTimeTotal> {

    @Getter
    private final String yearMonth;

    @Override
    public WorkDateAndTimeTotal getValueObject() {
        return new WorkDateAndTimeTotal(new WorkTotalYeaAndMonth(yearMonth));
    }
}
