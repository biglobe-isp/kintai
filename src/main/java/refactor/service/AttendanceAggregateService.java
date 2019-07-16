package refactor.service;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import refactor.domain.*;

@AllArgsConstructor
public class AttendanceAggregateService {
    @NonNull
    private final AttendanceRepository attendanceRepository;

    public TotalWorkingHours calculateTotalWorkingHours(YearMonth yearMonth) {
        MonthlyAttendanceRecord monthlyAttendanceRecord = attendanceRepository.findByYearMonth(yearMonth);
        return monthlyAttendanceRecord.calculateTotalWorkingHours();
    }

    public TotalOvertimeHours calculateTotalOvertimeHours(YearMonth yearMonth) {
        MonthlyAttendanceRecord monthlyAttendanceRecord = attendanceRepository.findByYearMonth(yearMonth);
        return monthlyAttendanceRecord.calculateTotalOvertimeHours();
    }
}
