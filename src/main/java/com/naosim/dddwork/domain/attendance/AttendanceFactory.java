package com.naosim.dddwork.domain.attendance;

import com.naosim.dddwork.domain.IAttendanceFactory;
import com.naosim.dddwork.domain.IOverTimeHoursCalculator;
import com.naosim.dddwork.domain.IWorkingHoursCalculator;
import com.naosim.dddwork.domain.TimeUnit;
import com.naosim.dddwork.domain.workregulations.WorkRegulations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
    public Attendance createForRegister(WorkDay workDay, VerifiedAttendanceTime attendanceTime, WorkRegulations workRegulations) {
        TimeUnit workingHours = iWorkingHoursCalculator.calcWorkingHours(attendanceTime, workRegulations);
        TimeUnit overTimeHours = iOverTimeHoursCalculator.calcOverTimeHours(workingHours, workRegulations);
        return create(workDay, attendanceTime, workingHours, overTimeHours);
    }

    @Override
    public Attendance createFromCsv(WorkDay workDay, VerifiedAttendanceTime attendanceTime, TimeUnit workingHours, TimeUnit overTimeHours) {
        return create(workDay, attendanceTime, workingHours, overTimeHours);
    }

    private Attendance create(WorkDay workDay, VerifiedAttendanceTime attendanceTime, TimeUnit workingHours, TimeUnit overTimeHours) {
        return new Attendance(workDay, attendanceTime, workingHours, overTimeHours);
    }
}
