package com.naosim.dddwork.api.form;

import com.naosim.dddwork.domain.attendance.WorkDate;
import jp.co.biglobe.lib.publication.date.DateParser;
import jp.co.biglobe.lib.publication.form.FormToValueObject;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@ToString(includeFieldNames = false)
@EqualsAndHashCode(callSuper = false)
public class WorkDateForm implements FormToValueObject<WorkDate> {

    @Getter
    @NotNull
    private String value;

    public WorkDateForm(String[] args) {

        if (args == null || args.length < 2) {
            throw new RuntimeException("引数が足りません");
        }

        this.value = args[1];
    }

    @Override
    public WorkDate getValueObject() {
        return new WorkDate(DateParser.parse_yyyyMMdd(this.getValue()));
    }
}
