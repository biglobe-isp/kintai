package com.naosim.dddwork.api.form;

import com.naosim.dddwork.api.ProcessingDivision;
import jp.co.biglobe.lib.publication.form.FormToValueObject;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@ToString(includeFieldNames = false)
@EqualsAndHashCode(callSuper = false)
public class ProcessingDivisionForm implements FormToValueObject<ProcessingDivision> {
    @Getter
    @NotNull
    private String value;

    public ProcessingDivisionForm(String processingDivision) {

        if (processingDivision == null || processingDivision.isEmpty() ) {
            throw new RuntimeException("引数[処理区分]が足りません");
        }

        this.value = processingDivision;
    }
    @Override
    public ProcessingDivision getValueObject() {
        return new ProcessingDivision(this.value);
    }
}
