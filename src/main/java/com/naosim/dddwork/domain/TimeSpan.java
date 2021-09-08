package com.naosim.dddwork.domain;

import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

@Getter
public class TimeSpan {
    private final int startTimeHour;
    private final int startTimeMinute;
    private final int endTimeHour;
    private final int endTimeMinute;

    public TimeSpan(String start, String end) {
        if (StringUtils.isEmpty(start) || !start.matches("[0-9]{4}")) {
            throw new RuntimeException("Invalid Parameter: start");
        }
        if (StringUtils.isEmpty(end) || !end.matches("[0-9]{4}")) {
            throw new RuntimeException("Invalid Parameter: end");
        }
        this.startTimeHour = Integer.valueOf(start.substring(0, 2));
        this.startTimeMinute = Integer.valueOf(start.substring(2, 4));
        this.endTimeHour = Integer.valueOf(end.substring(0, 2));
        this.endTimeMinute = Integer.valueOf(end.substring(2, 4));
    }

    public int getMinutes() {
        int minutes = (this.endTimeHour - this.startTimeHour) * 60;
        minutes += this.endTimeMinute - this.startTimeMinute;
        return minutes;
    }
}