package refactor.domain;

import lombok.NonNull;

import java.util.HashMap;
import java.util.Map;

public class MonthlyAttendanceRecord {
    private Map<WorkingDay, DailyAttendanceRecord> monthlyAttendanceRecords;

    public MonthlyAttendanceRecord() {
        monthlyAttendanceRecords = new HashMap<>();
    }

    public void add(@NonNull WorkingDay workingDay, @NonNull DailyAttendanceRecord dailyAttendanceRecord) {
        monthlyAttendanceRecords.put(workingDay, dailyAttendanceRecord);
    }

    public TotalActualWorkingHours calculateTotalActualWorkingHours() {
        int totalActualWorkingHours = monthlyAttendanceRecords.values().stream()
                .map(record -> record.getActualWorkingHours().getMinutes()).reduce(0, Integer::sum);
        return new TotalActualWorkingHours(totalActualWorkingHours);
    }

    public TotalOvertimeHours calculateTotalOvertimeHours() {
        int totalOvertimeHours = monthlyAttendanceRecords.values().stream()
                .map(record -> record.getOvertimeHours().getMinutes()).reduce(0, Integer::sum);
        return new TotalOvertimeHours(totalOvertimeHours);
    }
}
