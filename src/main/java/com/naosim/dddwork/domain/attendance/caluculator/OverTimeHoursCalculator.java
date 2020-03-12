package com.naosim.dddwork.domain.attendance.caluculator;

import com.naosim.dddwork.domain.attendance.AttendanceTime;
import com.naosim.dddwork.domain.service.WorkingHoursCalculable;
import com.naosim.dddwork.domain.TimePoint;
import com.naosim.dddwork.domain.TimeUnit;
import com.naosim.dddwork.domain.attendance.EndTime;
import com.naosim.dddwork.domain.attendance.OverTimeHours;
import com.naosim.dddwork.domain.attendance.StartTime;
import com.naosim.dddwork.domain.attendance.WorkingHours;
import com.naosim.dddwork.api.attendanceTime.VerifiedAttendanceTime;
import com.naosim.dddwork.domain.service.OverTimeHoursCalculable;
import com.naosim.dddwork.domain.workregulations.WorkRegulations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalTime;

@Component
public class OverTimeHoursCalculator implements OverTimeHoursCalculable {

    private WorkingHoursCalculable workingHoursCalculable;

    @Autowired
    public OverTimeHoursCalculator(WorkingHoursCalculable workingHoursCalculable) {
        this.workingHoursCalculable = workingHoursCalculable;
    }

    @Override
    public OverTimeHours calcOverTimeHours(
            WorkingHours workingHours, WorkRegulations workRegulations) {

        LocalTime start = workRegulations.getStartTimeRange().getStandard();
        LocalTime end = workRegulations.getEndTimeRange().getStandard();
        StartTime standardStart = StartTime.of(TimePoint.of(start.getHour(), start.getMinute()));
        EndTime standardEnd = EndTime.of(TimePoint.of(end.getHour(), end.getMinute()));

        AttendanceTime standardAttendanceTime = AttendanceTime.of(new VerifiedAttendanceTime(standardStart, standardEnd));

        WorkingHours standardWorkingTime = workingHoursCalculable.calcWorkingHours(standardAttendanceTime, workRegulations);

        int overTimeMinutes = workingHours.getTimeUnit().getTotalMinutes() - standardWorkingTime.getTimeUnit().getTotalMinutes();
        if (overTimeMinutes > 0) {
            return OverTimeHours.of(TimeUnit.of(overTimeMinutes));
        } else {
            return OverTimeHours.of(TimeUnit.of(0));
        }
    }
}
