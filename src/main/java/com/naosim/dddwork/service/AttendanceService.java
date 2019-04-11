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
    private final AttendanceBuilder attendanceBuilder;
    private final AttendanceSummaryBuilder attendanceSummaryBuilder;

    @Autowired
    public AttendanceService(
            AttendanceRepository attendanceRepository,
            AttendanceBuilder attendanceBuilder,
            AttendanceSummaryBuilder attendanceSummaryBuilder) {
        this.attendanceRepository = attendanceRepository;
        this.attendanceBuilder = attendanceBuilder;
        this.attendanceSummaryBuilder = attendanceSummaryBuilder;
    }

    public void saveAttendance(LocalDate date, TimePoint startTime, TimePoint endTime) {
        Attendance attendance = attendanceBuilder.build(date, startTime, endTime, LocalDate.now());
        attendanceRepository.save(attendance);
    }

    public AttendanceSummary fetchMonthlyAttendanceSummary(YearMonth yearMonth) {
        return attendanceSummaryBuilder.build(yearMonth, attendanceRepository.fetchMonthly(yearMonth));
    }
}
