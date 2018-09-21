package com.naosim.dddwork.datasource;

import com.naosim.dddwork.domain.AttendanceManagement;
import com.naosim.dddwork.domain.TotalWorkingHoursApplication;
import com.naosim.dddwork.domain.TotalWorkingHoursRepository;
import com.naosim.dddwork.domain.TotalWorkingHoursResult;

import java.util.List;

public class TotalWorkingHoursRepositoryImpl implements TotalWorkingHoursRepository {
    @Override
    public List<AttendanceManagement> get(TotalWorkingHoursApplication totalWorkingHoursApplication) {
        return null;
    }

    @Override
    public TotalWorkingHoursResult summary (List<AttendanceManagement> attendanceManagementList) {
        return null;
    }
}
