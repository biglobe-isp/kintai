package com.naosim.dddwork.api.form;

import com.naosim.dddwork.domain.attendance.EndTime;
import jp.co.biglobe.lib.publication.date.DateParser;
import jp.co.biglobe.lib.publication.form.FormToValueObject;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@ToString(includeFieldNames = false)
@EqualsAndHashCode(callSuper = false)
public class EndTimeForm implements FormToValueObject<EndTime> {

    @Getter
    @NotNull
    private String value;

    public EndTimeForm(String[] args) {

        if (args == null || args.length < 4) {
            throw new RuntimeException("引数が足りません");
        }

        this.value = args[3] + "00";
    }

    @Override
    public EndTime getValueObject() {
        return new EndTime(DateParser.parse_HHmmss(this.getValue()));
    }
}
