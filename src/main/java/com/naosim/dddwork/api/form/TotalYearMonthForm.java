package com.naosim.dddwork.api.form;

import com.naosim.dddwork.domain.attendance.TotalYearMonth;
import jp.co.biglobe.lib.publication.date.DateParser;
import jp.co.biglobe.lib.publication.form.FormToValueObject;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@ToString(includeFieldNames = false)
@EqualsAndHashCode(callSuper = false)
public class TotalYearMonthForm implements FormToValueObject<TotalYearMonth> {

    @Getter
    @NotNull
    private String value;

    public TotalYearMonthForm(String[] args) {

        if (args == null || args.length < 2) {
            throw new RuntimeException("引数が足りません");
        }

        this.value = args[1];
    }

    @Override
    public TotalYearMonth getValueObject() {
        return new TotalYearMonth(DateParser.parse_yyyyMM(this.getValue()));
    }
}
