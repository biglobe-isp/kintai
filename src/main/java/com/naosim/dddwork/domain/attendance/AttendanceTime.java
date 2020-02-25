package com.naosim.dddwork.domain.attendance;

import lombok.Value;

@Value(staticConstructor="of")
public class AttendanceTime {
    StartTime startTime;
    EndTime endTime;
}
