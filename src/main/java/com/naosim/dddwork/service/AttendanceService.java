package com.naosim.dddwork.service;

import com.naosim.dddwork.domain.AttendanceRepository;
import com.naosim.dddwork.domain.service.AttendanceGeneratable;
import com.naosim.dddwork.domain.service.WorkRegulationsGeneratable;
import com.naosim.dddwork.domain.attendance.Attendance;
import com.naosim.dddwork.domain.attendance.attendancetime.VerifiedAttendanceTime;
import com.naosim.dddwork.domain.attendance.WorkDay;
import com.naosim.dddwork.domain.workregulations.WorkRegulations;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AttendanceService {

    private final AttendanceRepository attendanceRepository;
    private final WorkRegulationsGeneratable workRegulationsGeneratable;
    private final AttendanceGeneratable attendanceFactory;

    public void registerAttendance(WorkDay workDay, VerifiedAttendanceTime attendanceTime) {
        WorkRegulations workRegulations = workRegulationsGeneratable.getCurrentRegulations();
        if (attendanceTime.isLateness(workRegulations)) {
            throw new RuntimeException("遅刻は認めません");
        }
        Attendance attendance = attendanceFactory.createForRegister(workDay, attendanceTime, workRegulations);
        attendanceRepository.save(attendance);
    }
}
