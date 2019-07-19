package refactor.service;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import refactor.domain.*;

@AllArgsConstructor
public class AttendanceAggregateService {
    @NonNull
    private final AttendanceRepository attendanceRepository;

    public TotalActualWorkingHours calculateTotalActualWorkingHours(ExtractionYearMonth extractionYearMonth) {
        MonthlyAttendanceRecord monthlyAttendanceRecord = attendanceRepository.findByExtractionYearMonth(extractionYearMonth);
        return monthlyAttendanceRecord.calculateTotalActualWorkingHours();
    }

    public TotalOvertimeHours calculateTotalOvertimeHours(ExtractionYearMonth extractionYearMonth) {
        MonthlyAttendanceRecord monthlyAttendanceRecord = attendanceRepository.findByExtractionYearMonth(extractionYearMonth);
        return monthlyAttendanceRecord.calculateTotalOvertimeHours();
    }
}
