package com.naosim.dddwork.kintai_management.api.form;

import com.naosim.dddwork.kintai_management.domain.word.HolidayKind;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@ToString(includeFieldNames = false)
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
public class HolidayKindForm {

    @Getter
    @Pattern(regexp = "^[0-9a-zA-Z]*$")
    private String value;

    public HolidayKind getValueObject() {
        return new HolidayKind(value);
    }
}

