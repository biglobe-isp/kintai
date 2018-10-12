package com.naosim.dddwork.api.form;

import com.naosim.dddwork.domain.StartingHours;
import jp.co.biglobe.lib.publication.form.FormToValueObject;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;


@ToString(includeFieldNames = false)
@EqualsAndHashCode(callSuper = false)
public class StartingHoursFrom implements FormToValueObject<StartingHours> {
    @Getter
    @NotNull
    private String value;

    public StartingHoursFrom(String startingHours) {
        if (startingHours == null || startingHours.isEmpty()) {
            throw new RuntimeException("引数[始業時間]が足りません");
        }

        this.value = startingHours;
    }

    @Override
    public StartingHours getValueObject() {
        return new StartingHours(LocalTime.parse(this.value, DateTimeFormatter.ofPattern("HHmm")));
    }
}
