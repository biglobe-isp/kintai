package refactor.domain;

import lombok.NonNull;

public interface AttendanceRepository {
    MonthlyAttendanceRecord findByExtractionYearMonth(@NonNull ExtractionYearMonth extractionYearMonth);

    void save(@NonNull DailyAttendanceRecord dailyAttendanceRecord);
}
