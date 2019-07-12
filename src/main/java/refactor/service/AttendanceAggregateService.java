package refactor.service;

import refactor.domain.*;

import java.util.Objects;

public class AttendanceAggregateService {
    private final AttendanceRepository attendanceRepository;

    public AttendanceAggregateService(AttendanceRepository attendanceRepository) {
        this.attendanceRepository = Objects.requireNonNull(attendanceRepository);
    }

    public TotalWorkingHours calculateTotalWorkingHours(YearMonth yearMonth) {
        MonthlyAttendanceRecord monthlyAttendanceRecord = attendanceRepository.findByYearMonth(yearMonth);
        return monthlyAttendanceRecord.calculateTotalWorkingHours();
    }

    public TotalOvertimeHours calculateTotalOvertimeHours(YearMonth yearMonth) {
        MonthlyAttendanceRecord monthlyAttendanceRecord = attendanceRepository.findByYearMonth(yearMonth);
        return monthlyAttendanceRecord.calculateTotalOvertimeHours();
    }
}
