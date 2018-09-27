package com.naosim.dddwork.domain.use_case;

import com.naosim.dddwork.domain.attendance.EndTime;
import com.naosim.dddwork.domain.attendance.StartTime;
import com.naosim.dddwork.domain.attendance.WorkDate;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@AllArgsConstructor
@EqualsAndHashCode
public class AttendanceInputApplication {

    @Getter
    private final WorkDate workDate;

    @Getter
    private final StartTime startTime;

    @Getter
    private final EndTime endTime;
}
