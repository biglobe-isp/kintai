package com.naosim.dddwork.domain.use_case;

import com.naosim.dddwork.domain.attendance.EndTime;
import com.naosim.dddwork.domain.attendance.InputDateTime;
import com.naosim.dddwork.domain.attendance.OverWorkMinutes;
import com.naosim.dddwork.domain.attendance.StartTime;
import com.naosim.dddwork.domain.attendance.WorkDate;
import com.naosim.dddwork.domain.attendance.WorkMinutes;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@AllArgsConstructor
@EqualsAndHashCode
public class AttendanceInputApplication {

    @Getter
    private WorkDate workDate;

    @Getter
    private StartTime startTime;

    @Getter
    private EndTime endTime;

    @Getter
    private WorkMinutes workMinutes;

    @Getter
    private OverWorkMinutes overWorkMinutes;

    @Getter
    private InputDateTime inputDateTime;
}
