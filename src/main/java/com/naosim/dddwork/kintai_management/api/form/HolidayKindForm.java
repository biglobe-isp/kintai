package com.naosim.dddwork.kintai_management.api.form;

import com.naosim.dddwork.kintai_management.domain.word.HolidayKind;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.Pattern;

@AllArgsConstructor
public class HolidayKindForm {

    @Getter
    @Pattern(regexp = "^[0-9a-zA-Z]*$")
    private String value;

    public HolidayKind getValueObject() {
        return new HolidayKind(value);
    }
}

