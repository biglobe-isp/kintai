package com.naosim.dddwork.domain.attendance;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.time.LocalTime;

/**
 * 退勤時刻
 */
@ToString(includeFieldNames = false)
@EqualsAndHashCode
@RequiredArgsConstructor
public class EndTime {

    @Getter
    private final LocalTime value;

    public EndTimeHours makeEndTimeHours() {
        return new EndTimeHours(this.getValue().getHour());
    }

    public EndTimeMinutes makeEndTimeMinutes() {
        return new EndTimeMinutes(this.getValue().getMinute());
    }
}
