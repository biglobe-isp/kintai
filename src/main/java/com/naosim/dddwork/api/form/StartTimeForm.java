package com.naosim.dddwork.api.form;

import com.naosim.dddwork.domain.attendance.StartTime;
import jp.co.biglobe.lib.publication.date.DateParser;
import jp.co.biglobe.lib.publication.form.FormToValueObject;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@ToString(includeFieldNames = false)
@EqualsAndHashCode(callSuper = false)
public class StartTimeForm implements FormToValueObject<StartTime> {

    @Getter
    @NotNull
    private String value;

    public StartTimeForm(String[] args) {

        if (args == null || args.length < 3) {
            throw new RuntimeException("引数が足りません");
        }

        this.value = args[2] + "00";
    }

    @Override
    public StartTime getValueObject() {
        return new StartTime(DateParser.parse_HHmmss(this.getValue()));
    }
}
