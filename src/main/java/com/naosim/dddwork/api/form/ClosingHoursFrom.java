package com.naosim.dddwork.api.form;

import com.naosim.dddwork.domain.ClosingHours;
import jp.co.biglobe.lib.publication.form.FormToValueObject;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@ToString(includeFieldNames = false)
@EqualsAndHashCode(callSuper = false)
public class ClosingHoursFrom implements FormToValueObject<ClosingHours> {
    @Getter
    @NotNull
    private String value;

    public ClosingHoursFrom(String closingHours) {

        if (closingHours == null || closingHours.isEmpty()) {
            throw new RuntimeException("引数[終業時間]が足りません");
        }
        this.value = closingHours;
    }

    @Override
    public ClosingHours getValueObject() {
        return new ClosingHours(LocalTime.parse(this.value, DateTimeFormatter.ofPattern("HHmm")));
    }
}
