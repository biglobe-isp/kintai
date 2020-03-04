package com.naosim.dddwork.service;

import com.naosim.dddwork.domain.AttendanceRepository;
import com.naosim.dddwork.domain.IAttendanceFactory;
import com.naosim.dddwork.domain.WorkRegulationsRepository;
import com.naosim.dddwork.domain.attendance.Attendance;
import com.naosim.dddwork.domain.attendance.VerifiedAttendanceTime;
import com.naosim.dddwork.domain.attendance.WorkDay;
import com.naosim.dddwork.domain.workregulations.WorkRegulations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AttendanceService {

    private final AttendanceRepository attendanceRepository;
    private final WorkRegulationsRepository workRegulationsRepository;
    private final IAttendanceFactory attendanceFactory;

    @Autowired
    public AttendanceService(AttendanceRepository attendanceRepository,
                             WorkRegulationsRepository workRegulationsRepository,
                             IAttendanceFactory attendanceFactory) {
        this.attendanceRepository = attendanceRepository;
        this.workRegulationsRepository = workRegulationsRepository;
        this.attendanceFactory = attendanceFactory;
    }

    public void registerAttendance(WorkDay workDay, VerifiedAttendanceTime attendanceTime) {
        WorkRegulations workRegulations = workRegulationsRepository.getCurrentRegulations();
        if (attendanceTime.isLateness(workRegulations)) {
            throw new RuntimeException("遅刻は認めません");
        }
        Attendance attendance = attendanceFactory.createForRegister(workDay, attendanceTime, workRegulations);
        attendanceRepository.save(attendance);
    }
}
