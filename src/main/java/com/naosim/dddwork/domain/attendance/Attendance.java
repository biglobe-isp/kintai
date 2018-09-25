package com.naosim.dddwork.domain.attendance;

import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

/**
 * 勤怠
 */
@ToString(includeFieldNames = false)
@EqualsAndHashCode
@RequiredArgsConstructor
public class Attendance {

    public void input(AttendanceDetail attendanceDetail) {
    }

    public WorkMinutesByMonth total() {
        return null;
    }
}
