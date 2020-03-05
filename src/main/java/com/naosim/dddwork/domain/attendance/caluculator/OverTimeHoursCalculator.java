package com.naosim.dddwork.domain.attendance.caluculator;

import com.naosim.dddwork.domain.service.WorkingHoursCalculable;
import com.naosim.dddwork.domain.TimePoint;
import com.naosim.dddwork.domain.TimeUnit;
import com.naosim.dddwork.domain.attendance.EndTime;
import com.naosim.dddwork.domain.attendance.OverTimeHours;
import com.naosim.dddwork.domain.attendance.StartTime;
import com.naosim.dddwork.domain.attendance.WorkingHours;
import com.naosim.dddwork.domain.attendance.attendancetime.VerifiedAttendanceTime;
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

        LocalTime standardStart = workRegulations.getStartTimeRange().getStandard();

        LocalTime standardEnd = workRegulations.getEndTimeRange().getStandard();

        VerifiedAttendanceTime standardAttendanceTime = VerifiedAttendanceTime.of(
                StartTime.of(TimePoint.of(standardStart.getHour(), standardStart.getMinute())),
                EndTime.of(TimePoint.of(standardEnd.getHour(), standardEnd.getMinute())));

        WorkingHours standardWorkingTime = workingHoursCalculable.calcWorkingHours(standardAttendanceTime, workRegulations);

        int overTimeMinutes = workingHours.getTimeUnit().getTotalMinutes() - standardWorkingTime.getTimeUnit().getTotalMinutes();
        if (overTimeMinutes > 0) {
            return OverTimeHours.of(TimeUnit.of(overTimeMinutes));
        } else {
            return OverTimeHours.of(TimeUnit.of(0));
        }
    }
}
