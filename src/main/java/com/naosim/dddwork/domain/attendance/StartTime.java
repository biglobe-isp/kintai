package com.naosim.dddwork.domain.attendance;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.time.LocalTime;

/**
 * 出勤時刻
 */
@ToString(includeFieldNames = false)
@EqualsAndHashCode
@RequiredArgsConstructor
public class StartTime {

    @Getter
    private final LocalTime value;

    public StartTimeHours makeStartTimeHours() {
        return new StartTimeHours(this.getValue().getHour());
    }

    public StartTimeMinutes makeStartTimeMinutes() {
        return new StartTimeMinutes(this.getValue().getMinute());
    }
}
