package com.naosim.dddwork.service;

import com.naosim.dddwork.domain.AttendanceRepository;
import com.naosim.dddwork.domain.attendance.AttendanceTime;
import com.naosim.dddwork.api.attendancetime.VerifiedAttendanceTime;
import com.naosim.dddwork.domain.service.AttendanceGeneratableForRegister;
import com.naosim.dddwork.domain.service.WorkRegulationsGeneratable;
import com.naosim.dddwork.domain.attendance.Attendance;
import com.naosim.dddwork.domain.attendance.WorkDay;
import com.naosim.dddwork.domain.workregulations.WorkRegulations;
import com.naosim.dddwork.exception.InvalidAttendanceException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AttendanceService {

    private final AttendanceRepository attendanceRepository;
    private final WorkRegulationsGeneratable workRegulationsGeneratable;
    private final AttendanceGeneratableForRegister attendanceGenerator;

    public void registerAttendance(WorkDay workDay, VerifiedAttendanceTime verifiedAttendanceTime) throws InvalidAttendanceException {
        WorkRegulations workRegulations = workRegulationsGeneratable.getCurrentRegulations();
        AttendanceTime attendanceTime = AttendanceTime.of(verifiedAttendanceTime);
        Attendance attendance = attendanceGenerator.create(workDay, attendanceTime, workRegulations);
        attendanceRepository.save(attendance);
    }
}
