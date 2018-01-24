package com.naosim.dddwork.api.form;

import com.naosim.dddwork.domain.KintaiTotalPrintInput;
import com.naosim.dddwork.domain.time.work.WorkYearMonth;
import jp.co.biglobe.lib.publication.form.FormToValueObject;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@EqualsAndHashCode
public class KintaiTotalPrintInputForm implements FormToValueObject<KintaiTotalPrintInput> {

    @Getter
    private final String[] args;

    @Override
    public KintaiTotalPrintInput getValueObject() {

        if (!this.isEnoughArgsLength()) {
            throw new RuntimeException("引数が足りません");
        }

        return new KintaiTotalPrintInput(new WorkYearMonth(this.args[1]));
    }

    private boolean isEnoughArgsLength() {
        return this.args.length >= 2;
    }
}
