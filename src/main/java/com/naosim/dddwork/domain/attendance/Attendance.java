package com.naosim.dddwork.domain.attendance;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

/**
 * 勤怠
 */
@ToString(includeFieldNames = false)
@EqualsAndHashCode
@RequiredArgsConstructor
public class Attendance {

    @Getter
    private final WorkDate workDate;

    @Getter
    private final StartTime startTime;

    @Getter
    private final EndTime endTime;

    @Getter
    private final WorkMinutes workMinutes;

    @Getter
    private final OverWorkMinutes overWorkMinutes;

    public static Attendance create(WorkDate workDate, StartTime startTime, EndTime endTime) {

        WorkMinutes workMinutes = new WorkMinutes(startTime, endTime);

        return new Attendance(
                workDate,
                startTime,
                endTime,
                workMinutes,
                new OverWorkMinutes(workMinutes)
        );
    }
}
