package com.naosim.dddwork.domain.attendance;

import com.naosim.dddwork.domain.service.AttendanceGeneratableForRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AttendanceGeneratorForRepository implements AttendanceGeneratableForRepository {
    @Override
    public Attendance create(WorkDay workDay, AttendanceTime attendanceTime,
                             WorkingHours workingHours, OverTimeHours overTimeHours) {
        return new Attendance(workDay, attendanceTime, workingHours, overTimeHours);
    }
}
