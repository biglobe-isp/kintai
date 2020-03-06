package com.naosim.dddwork.service;

import com.naosim.dddwork.domain.AttendanceRepository;
import com.naosim.dddwork.domain.service.AttendanceGeneratableForRegister;
import com.naosim.dddwork.domain.service.WorkRegulationsGeneratable;
import com.naosim.dddwork.domain.attendance.Attendance;
import com.naosim.dddwork.domain.attendance.attendancetime.VerifiedAttendanceTime;
import com.naosim.dddwork.domain.attendance.WorkDay;
import com.naosim.dddwork.domain.workregulations.WorkRegulations;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AttendanceService {

    private final AttendanceRepository attendanceRepository;
    private final WorkRegulationsGeneratable workRegulationsGeneratable;
    private final AttendanceGeneratableForRegister attendanceGenerator;

    public void registerAttendance(WorkDay workDay, VerifiedAttendanceTime attendanceTime) {
        WorkRegulations workRegulations = workRegulationsGeneratable.getCurrentRegulations();
        Attendance attendance = attendanceGenerator.create(workDay, attendanceTime, workRegulations);
        attendanceRepository.save(attendance);
    }
}
