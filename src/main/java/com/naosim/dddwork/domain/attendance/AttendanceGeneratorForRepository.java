package com.naosim.dddwork.domain.attendance;

import com.naosim.dddwork.domain.attendance.attendancetime.VerifiedAttendanceTime;
import com.naosim.dddwork.domain.service.AttendanceGeneratableForRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AttendanceGeneratorForRepository implements AttendanceGeneratableForRepository {
    @Override
    public Attendance create(WorkDay workDay, VerifiedAttendanceTime attendanceTime,
                             WorkingHours workingHours, OverTimeHours overTimeHours) {
        return new Attendance(workDay, attendanceTime, workingHours, overTimeHours);
    }
}
