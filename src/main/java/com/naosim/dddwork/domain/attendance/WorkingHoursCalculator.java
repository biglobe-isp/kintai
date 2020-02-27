package com.naosim.dddwork.domain.attendance;

import com.naosim.dddwork.domain.IWorkingHoursCalculator;
import com.naosim.dddwork.domain.TimeRange;
import com.naosim.dddwork.domain.TimeUnit;
import com.naosim.dddwork.domain.workregulations.WorkRegulations;
import org.springframework.stereotype.Component;

@Component
public class WorkingHoursCalculator implements IWorkingHoursCalculator {
    @Override
    public TimeUnit calcWorkingHours(
            AttendanceTime attendanceTime, WorkRegulations workRegulations) {

        BreakTimeHours breakTimeHours = BreakTimeHours.of(attendanceTime, workRegulations);

        TimeRange attendanceTimeRange = TimeRange.of(attendanceTime.getStartTime().getTimePoint(),
                                                     attendanceTime.getEndTime().getDeemedEndTime());

        int workingMinutes = attendanceTimeRange.getRangeMinutes() - breakTimeHours.getTimeUnit().getTotalMinutes();

        return TimeUnit.of(workingMinutes);
    }
}
