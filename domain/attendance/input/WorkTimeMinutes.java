package com.naosim.dddwork.domain.attendance.input;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;

public class WorkTimeMinutes {
    private static final HashMap<LocalTime, LocalTime> REST_TIME_MAP = new HashMap<LocalTime, LocalTime>() {
        {
            put(LocalTime.of(12, 0), LocalTime.of(13, 0));
            put(LocalTime.of(18, 0), LocalTime.of(19, 0));
            put(LocalTime.of(21, 0), LocalTime.of(22, 0));
        }
    };
    private final Integer value;

    public WorkTimeMinutes(WorkStartTime workStartTime, WorkEndTime workEndTime) {
        Long workTimeMinutes = ChronoUnit.MINUTES.between(workStartTime.getValue(), workEndTime.getValue());

        //休憩時間を引く
        for (LocalTime s : REST_TIME_MAP.keySet()) {
            LocalTime e = REST_TIME_MAP.get(s);
            if (workEndTime.getValue().isAfter(s) && workStartTime.getValue().isBefore(e)) {
                workTimeMinutes -= ChronoUnit.MINUTES.between(
                        workStartTime.getValue().isAfter(s) ? workStartTime.getValue() : s,
                        workEndTime.getValue().isBefore(e) ? workEndTime.getValue() : e
                );
            }
        }

        if (workTimeMinutes < 0) {
            throw new IllegalArgumentException("WorkTimeMinutes value can not be less than 0");
        }
        this.value = workTimeMinutes.intValue();
    }

    public int getValue() {
        return value;
    }
}
