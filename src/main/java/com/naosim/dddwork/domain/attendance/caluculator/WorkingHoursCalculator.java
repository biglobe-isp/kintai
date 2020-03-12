package com.naosim.dddwork.domain.attendance.caluculator;

import com.naosim.dddwork.domain.TimeRange;
import com.naosim.dddwork.domain.TimeUnit;
import com.naosim.dddwork.domain.attendance.AttendanceTime;
import com.naosim.dddwork.domain.attendance.BreakTimeHours;
import com.naosim.dddwork.domain.attendance.WorkingHours;
import com.naosim.dddwork.domain.service.WorkingHoursCalculable;
import com.naosim.dddwork.domain.workregulations.WorkRegulations;
import org.springframework.stereotype.Component;

@Component
public class WorkingHoursCalculator implements WorkingHoursCalculable {
    @Override
    public WorkingHours calcWorkingHours(
            AttendanceTime attendanceTime, WorkRegulations workRegulations) {

        BreakTimeHours breakTimeHours = BreakTimeHours.of(attendanceTime, workRegulations);

        TimeRange attendanceTimeRange = TimeRange.of(attendanceTime.getStartTime().getTimePoint(),
                                                     attendanceTime.getEndTime().getDeemedEndTime());

        int workingMinutes = attendanceTimeRange.getRangeMinutes() - breakTimeHours.getTimeUnit().getTotalMinutes();

        return WorkingHours.of(TimeUnit.of(workingMinutes));
    }
}
