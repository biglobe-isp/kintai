package com.naosim.dddwork.domain.attendance;

import com.naosim.dddwork.domain.service.AttendanceGeneratable;
import com.naosim.dddwork.domain.service.OverTimeHoursCalculable;
import com.naosim.dddwork.domain.service.WorkingHoursCalculable;
import com.naosim.dddwork.domain.attendance.attendancetime.VerifiedAttendanceTime;
import com.naosim.dddwork.domain.workregulations.WorkRegulations;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AttendanceGenerator implements AttendanceGeneratable {

    private final WorkingHoursCalculable workingHoursCalculable;
    private final OverTimeHoursCalculable overTimeHoursCalculable;

    @Override
    public Attendance createForRegister(WorkDay workDay, VerifiedAttendanceTime attendanceTime, WorkRegulations workRegulations) {
        WorkingHours workingHours = workingHoursCalculable.calcWorkingHours(attendanceTime, workRegulations);
        OverTimeHours overTimeHours = overTimeHoursCalculable.calcOverTimeHours(workingHours, workRegulations);
        return create(workDay, attendanceTime, workingHours, overTimeHours);
    }

    @Override
    public Attendance createFromCsv(WorkDay workDay, VerifiedAttendanceTime attendanceTime, WorkingHours workingHours, OverTimeHours overTimeHours) {
        return create(workDay, attendanceTime, workingHours, overTimeHours);
    }

    private Attendance create(WorkDay workDay, VerifiedAttendanceTime attendanceTime, WorkingHours workingHours, OverTimeHours overTimeHours) {
        return new Attendance(workDay, attendanceTime, workingHours, overTimeHours);
    }
}
