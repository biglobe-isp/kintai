package com.naosim.dddwork.api.form;

import com.naosim.dddwork.domain.kintai.KintaiTotalPrintTargetYearMonth;
import com.naosim.dddwork.domain.kintai.time.work.WorkYearMonth;
import jp.co.biglobe.lib.publication.form.FormToValueObject;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@EqualsAndHashCode
public class KintaiTotalPrintInputForm implements FormToValueObject<KintaiTotalPrintTargetYearMonth> {

    @Getter
    private final String[] args;

    @Override
    public KintaiTotalPrintTargetYearMonth getValueObject() {

        if (!this.isEnoughArgsLength()) {
            throw new RuntimeException("引数が足りません");
        }

        return new KintaiTotalPrintTargetYearMonth(new WorkYearMonth(this.args[1]));
    }

    private boolean isEnoughArgsLength() {
        return this.args.length >= 2;
    }
}
