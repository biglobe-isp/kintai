package com.naosim.dddwork.domain.attendance;

import com.naosim.dddwork.domain.IOverTimeHoursCalculator;
import com.naosim.dddwork.domain.IWorkingHoursCalculator;
import com.naosim.dddwork.domain.TimePoint;
import com.naosim.dddwork.domain.TimeUnit;
import com.naosim.dddwork.domain.workregulations.WorkRegulations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalTime;

@Component
public class OverTimeHoursCalculator implements IOverTimeHoursCalculator {

    private IWorkingHoursCalculator iWorkingHoursCalculator;

    @Autowired
    public OverTimeHoursCalculator(IWorkingHoursCalculator iWorkingHoursCalculator) {
        this.iWorkingHoursCalculator = iWorkingHoursCalculator;
    }

    @Override
    public TimeUnit calcOverTimeHours(
            TimeUnit workingHours, WorkRegulations workRegulations) {

        LocalTime standardStart = workRegulations.getStartTimeRange().getStandard();

        LocalTime standardEnd = workRegulations.getEndTimeRange().getStandard();

        AttendanceTime standardAttendanceTime = AttendanceTime.of(
                StartTime.of(TimePoint.of(standardStart.getHour(), standardStart.getMinute())),
                EndTime.of(TimePoint.of(standardEnd.getHour(), standardEnd.getMinute())));

        TimeUnit standardWorkingTime = iWorkingHoursCalculator.calcWorkingHours(standardAttendanceTime, workRegulations);

        int overTimeMinutes = workingHours.getTotalMinutes() - standardWorkingTime.getTotalMinutes();
        if (overTimeMinutes > 0) {
            return TimeUnit.of(overTimeMinutes);
        } else {
            return TimeUnit.of(0);
        }
    }
}
