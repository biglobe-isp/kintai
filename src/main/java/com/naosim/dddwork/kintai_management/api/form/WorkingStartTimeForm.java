package com.naosim.dddwork.kintai_management.api.form;

import com.naosim.dddwork.kintai_management.domain.word.WorkingStartTime;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@AllArgsConstructor
public class WorkingStartTimeForm  {

    @Getter
    @Size(min = 4, max = 4)
    @Pattern(regexp = "^[0-9]*$")
    private String value;

    public WorkingStartTime getValueObject() {
        return new WorkingStartTime(value);
    }
}

