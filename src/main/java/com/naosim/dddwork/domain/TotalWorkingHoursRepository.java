package com.naosim.dddwork.domain;

import java.util.List;

public interface TotalWorkingHoursRepository {
    List<AttendanceManagement> get (TotalWorkingHoursApplication totalWorkingHoursApplication); // 取得
    TotalWorkingHoursResult summary (List<AttendanceManagement> attendanceManagementList); // 集計
}
