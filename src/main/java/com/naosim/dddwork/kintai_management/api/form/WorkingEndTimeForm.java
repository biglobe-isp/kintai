package com.naosim.dddwork.kintai_management.api.form;

import com.naosim.dddwork.kintai_management.domain.word.WorkingEndTime;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@ToString(includeFieldNames = false)
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
public class WorkingEndTimeForm {

    @Getter
    @Size(min = 4, max = 4)
    @Pattern(regexp = "^[0-9]*$")
    private String value;

    public WorkingEndTime getValueObject() {
        return new WorkingEndTime(value);
    }
}

