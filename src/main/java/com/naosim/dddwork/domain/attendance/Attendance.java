package com.naosim.dddwork.domain.attendance;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@AllArgsConstructor
@EqualsAndHashCode
public class Attendance {

    @Getter
    private final WorkDay workDay;

    @Getter
    private AttendanceTime attendanceTime;

    @Getter
    private WorkingHours workingHours;

    @Getter
    private OverTimeHours overTimeHours;

}
