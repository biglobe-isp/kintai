package com.naosim.dddwork.domain.attendance;

import com.naosim.dddwork.domain.TimeUnit;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.time.LocalDate;

@AllArgsConstructor
@EqualsAndHashCode
public class Attendance {

    @Getter
    private final LocalDate workDay;

    @Getter
    private AttendanceTime attendanceTime;

    @Getter
    private TimeUnit workingHours;

    @Getter
    private TimeUnit overTimeHours;

}
