package refactor.domain;

import lombok.AllArgsConstructor;
import lombok.NonNull;

import java.util.List;

@AllArgsConstructor
public class MonthlyAttendanceRecord {
    @NonNull
    private final List<DailyAttendanceRecord> monthlyAttendanceRecords;

    public TotalActualWorkingHours calculateTotalActualWorkingHours() {
        int totalActualWorkingHours = monthlyAttendanceRecords.stream()
                .map(record -> record.getActualWorkingHours().getMinutes()).reduce(0, Integer::sum);
        return new TotalActualWorkingHours(totalActualWorkingHours);
    }

    public TotalOvertimeHours calculateTotalOvertimeHours() {
        int totalOvertimeHours = monthlyAttendanceRecords.stream()
                .map(record -> record.getOvertimeHours().getMinutes()).reduce(0, Integer::sum);
        return new TotalOvertimeHours(totalOvertimeHours);
    }
}
