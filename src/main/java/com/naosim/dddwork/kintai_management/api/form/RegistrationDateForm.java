package com.naosim.dddwork.kintai_management.api.form;

import com.naosim.dddwork.kintai_management.domain.word.RegistrationDate;
import jp.co.biglobe.lib.publication.validation.FormatDate;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class RegistrationDateForm {

    @Getter
    @FormatDate
    private String value;

    public RegistrationDate getValueObject() {
        return new RegistrationDate(value);
    }
}

