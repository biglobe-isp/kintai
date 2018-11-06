package com.naosim.dddwork.domain;

import com.naosim.dddwork.domain.use_case.TotalWorkingHoursApplication;

public interface AttendanceRepository {
    void register(DailyAttendance attendance);

    MonthlyAttendance get(TotalWorkingHoursApplication totalWorkingHoursApplication); // 取得
}
