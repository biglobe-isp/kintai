package refactor.service;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import refactor.domain.*;

@AllArgsConstructor
public class AttendanceAggregateService {
    @NonNull
    private final AttendanceRepository attendanceRepository;

    public TotalActualWorkingHours calculateTotalActualWorkingHours(YearMonth yearMonth) {
        MonthlyAttendanceRecord monthlyAttendanceRecord = attendanceRepository.findByYearMonth(yearMonth);
        return monthlyAttendanceRecord.calculateTotalActualWorkingHours();
    }

    public TotalOvertimeHours calculateTotalOvertimeHours(YearMonth yearMonth) {
        MonthlyAttendanceRecord monthlyAttendanceRecord = attendanceRepository.findByYearMonth(yearMonth);
        return monthlyAttendanceRecord.calculateTotalOvertimeHours();
    }
}
