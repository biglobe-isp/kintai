package com.naosim.dddwork.api.form;

import com.naosim.dddwork.domain.WorkDay;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.validation.constraints.NotNull;
import jp.co.biglobe.lib.publication.form.FormToValueObject;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@ToString(includeFieldNames = false)
@EqualsAndHashCode(callSuper = false)
public class WorkDayFrom implements FormToValueObject<WorkDay> {
    @Getter
    @NotNull
    private String value;

    public WorkDayFrom(String workDay) {
        if (workDay == null || workDay.isEmpty()) {
            throw new RuntimeException("引数[勤務日]が足りません");
        }
        //-date:20170101 -start:0900 -end:1800
        this.value = workDay;
    }

    @Override
    public WorkDay getValueObject() {
        return new WorkDay(LocalDate.parse(this.value, DateTimeFormatter.ofPattern("yyyyMMdd")));
    }
}
