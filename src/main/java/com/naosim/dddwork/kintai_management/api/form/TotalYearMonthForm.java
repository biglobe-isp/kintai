package com.naosim.dddwork.kintai_management.api.form;

import com.naosim.dddwork.kintai_management.domain.word.TotalYearMonth;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@ToString(includeFieldNames = false)
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
public class TotalYearMonthForm {

    @Getter
    @Size(min = 6, max = 6)
    @Pattern(regexp = "^[0-9]*$")
    private String value;

    public TotalYearMonth getValueObject() {
        return new TotalYearMonth(value);
    }
}

