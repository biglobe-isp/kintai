package com.naosim.dddwork.domain.attendance;

import com.naosim.dddwork.domain.TimePoint;
import com.naosim.dddwork.domain.service.AttendanceGeneratableForRegister;
import com.naosim.dddwork.domain.service.OverTimeHoursCalculable;
import com.naosim.dddwork.domain.service.WorkingHoursCalculable;
import com.naosim.dddwork.domain.workregulations.WorkRegulations;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AttendanceGeneratorForRegister implements AttendanceGeneratableForRegister {

    private final WorkingHoursCalculable workingHoursCalculable;
    private final OverTimeHoursCalculable overTimeHoursCalculable;

    @Override
    public Attendance create(WorkDay workDay, AttendanceTime attendanceTime, WorkRegulations workRegulations) {
        if (isLateness(attendanceTime.getStartTime(), workRegulations)) {
            throw new RuntimeException("遅刻は認めません");
        }

        WorkingHours workingHours = workingHoursCalculable.calcWorkingHours(attendanceTime, workRegulations);
        OverTimeHours overTimeHours = overTimeHoursCalculable.calcOverTimeHours(workingHours, workRegulations);
        return new Attendance(workDay, attendanceTime, workingHours, overTimeHours);
    }

    private boolean isLateness(StartTime startTime, WorkRegulations workRegulations) {
        TimePoint maxTimePoint = workRegulations.getStartTimeRange().getRange().getTimeTo();
        TimePoint start = startTime.getTimePoint();

        return start.getMinutesValue() > maxTimePoint.getMinutesValue();
    }
}
