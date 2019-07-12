package refactor.domain;

import java.util.HashMap;
import java.util.Map;

public class MonthlyAttendanceRecord {
    private Map<WorkingDay, DailyAttendanceRecord> monthlyAttendanceRecords;

    public MonthlyAttendanceRecord() {
        monthlyAttendanceRecords = new HashMap<>();
    }

    public void add(WorkingDay workingDay, DailyAttendanceRecord dailyAttendanceRecord) {
        monthlyAttendanceRecords.put(workingDay, dailyAttendanceRecord);
    }

    public TotalWorkingHours calculateTotalWorkingHours() {
        int totalWorkingHours = monthlyAttendanceRecords.values().stream()
                .map(record -> record.getWorkingHours().inMinutes()).reduce(0, Integer::sum);
        return new TotalWorkingHours(totalWorkingHours);
    }

    public TotalOvertimeHours calculateTotalOvertimeHours() {
        int totalOvertimeHours = monthlyAttendanceRecords.values().stream()
                .map(record -> record.getOvertimeHours().inMinutes()).reduce(0, Integer::sum);
        return new TotalOvertimeHours(totalOvertimeHours);
    }
}
