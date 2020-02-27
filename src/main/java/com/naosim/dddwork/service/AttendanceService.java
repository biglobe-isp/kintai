package com.naosim.dddwork.service;

import com.naosim.dddwork.domain.AttendanceRepository;
import com.naosim.dddwork.domain.IAttendanceFactory;
import com.naosim.dddwork.domain.attendance.Attendance;
import com.naosim.dddwork.domain.attendance.AttendanceTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class AttendanceService {

    private IAttendanceFactory iAttendanceFactory;
    private AttendanceRepository attendanceRepository;

    @Autowired
    public AttendanceService(IAttendanceFactory iAttendanceFactory,
                             AttendanceRepository attendanceRepository) {
        this.iAttendanceFactory = iAttendanceFactory;
        this.attendanceRepository = attendanceRepository;
    }

    public void registerAttendance(LocalDate workDay, AttendanceTime attendanceTime) {
        Attendance attendance = iAttendanceFactory.createForRegister(workDay, attendanceTime);
        attendanceRepository.save(attendance);
    }
}
