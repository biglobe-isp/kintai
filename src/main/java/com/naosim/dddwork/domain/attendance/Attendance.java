package com.naosim.dddwork.domain.attendance;

import com.naosim.dddwork.domain.TimeUnit;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@AllArgsConstructor
@EqualsAndHashCode
public class Attendance {

    @Getter
    private final WorkDay workDay;

    @Getter
    private VerifiedAttendanceTime attendanceTime;

    @Getter
    private TimeUnit workingHours;

    @Getter
    private TimeUnit overTimeHours;

}
