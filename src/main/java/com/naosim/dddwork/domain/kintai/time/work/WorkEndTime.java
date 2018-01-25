package com.naosim.dddwork.domain.kintai.time.work;

import com.naosim.dddwork.domain.kintai.time.Time;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@EqualsAndHashCode
@ToString
public class WorkEndTime {

    @Getter
    private final String value;

    @Getter
    private final Time time;

    public WorkEndTime(String value) {
        this.value = value;
        this.time = Time.convertToTime(this.value);
    }

}
