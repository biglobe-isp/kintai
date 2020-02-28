package com.naosim.dddwork.domain.attendance;

import com.naosim.dddwork.domain.IAttendanceFactory;
import com.naosim.dddwork.domain.IOverTimeHoursCalculator;
import com.naosim.dddwork.domain.IWorkingHoursCalculator;
import com.naosim.dddwork.domain.TimePoint;
import com.naosim.dddwork.domain.TimeUnit;
import com.naosim.dddwork.domain.workregulations.WorkRegulations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalTime;

@Component
public class AttendanceFactory implements IAttendanceFactory {

    private IWorkingHoursCalculator iWorkingHoursCalculator;
    private IOverTimeHoursCalculator iOverTimeHoursCalculator;

    @Autowired
    public AttendanceFactory(IWorkingHoursCalculator iWorkingHoursCalculator,
                             IOverTimeHoursCalculator iOverTimeHoursCalculator) {
        this.iWorkingHoursCalculator = iWorkingHoursCalculator;
        this.iOverTimeHoursCalculator = iOverTimeHoursCalculator;
    }

    @Override
    public Attendance createForRegister(WorkDay workDay, AttendanceTime attendanceTime, WorkRegulations workRegulations) {
        if (isLateness(attendanceTime, workRegulations)) {
            return null;
        }
        TimeUnit workingHours = iWorkingHoursCalculator.calcWorkingHours(attendanceTime, workRegulations);
        TimeUnit overTimeHours = iOverTimeHoursCalculator.calcOverTimeHours(workingHours, workRegulations);
        return create(workDay, attendanceTime, workingHours, overTimeHours);
    }

    @Override
    public Attendance createFromCsv(WorkDay workDay, AttendanceTime attendanceTime, TimeUnit workingHours, TimeUnit overTimeHours) {
        return create(workDay, attendanceTime, workingHours, overTimeHours);
    }

    private Attendance create(WorkDay workDay, AttendanceTime attendanceTime, TimeUnit workingHours, TimeUnit overTimeHours) {
        return new Attendance(workDay, attendanceTime, workingHours, overTimeHours);
    }

    public boolean isLateness(AttendanceTime attendanceTime, WorkRegulations workRegulations) {
        TimePoint maxTimePoint = workRegulations.getStartTimeRange().getRange().getTimeTo();
        LocalTime maxTime = LocalTime.of(maxTimePoint.getHour(), maxTimePoint.getMinutes());
        TimePoint start = attendanceTime.getStartTime().getTimePoint();
        LocalTime compareTime = LocalTime.of(start.getHour(), start.getMinutes());

        return maxTime.compareTo(compareTime) < 0;
    }
}
