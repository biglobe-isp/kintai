package com.naosim.dddwork.api.form;

import com.naosim.dddwork.domain.word.WorkDate;
import jp.co.biglobe.lib.publication.validation.FormatDate;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

/**
 * 対象年月日Form
 */
@ToString(includeFieldNames = false)
@EqualsAndHashCode(callSuper = false)
public class WorkDateForm {

    @Getter
    @FormatDate
    private String value;

    public WorkDateForm(String value) {
        this.value = value;
    }

    public WorkDate getValueObject() {
        return new WorkDate(value);
    }
}
