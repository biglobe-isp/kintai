package com.naosim.dddwork.service;

import com.naosim.dddwork.domain.Attendance;
import com.naosim.dddwork.domain.AttendanceRepository;
import com.naosim.dddwork.domain.AttendanceSummary;
import com.naosim.dddwork.domain.TimePoint;
import com.naosim.dddwork.domain.YearMonth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class AttendanceService {

    private final AttendanceRepository attendanceRepository;
    private final AttendanceFactory attendanceFactory;
    private final AttendanceSummaryFactory attendanceSummaryFactory;

    @Autowired
    public AttendanceService(
            AttendanceRepository attendanceRepository,
            AttendanceFactory attendanceFactory,
            AttendanceSummaryFactory attendanceSummaryFactory) {
        this.attendanceRepository = attendanceRepository;
        this.attendanceFactory = attendanceFactory;
        this.attendanceSummaryFactory = attendanceSummaryFactory;
    }

    public void saveAttendance(LocalDate date, TimePoint startTime, TimePoint endTime) {
        Attendance attendance = attendanceFactory.create(date, startTime, endTime, LocalDate.now());
        attendanceRepository.save(attendance);
    }

    public AttendanceSummary fetchMonthlyAttendanceSummary(YearMonth yearMonth) {
        return attendanceSummaryFactory.create(yearMonth, attendanceRepository.fetchMonthly(yearMonth));
    }
}
