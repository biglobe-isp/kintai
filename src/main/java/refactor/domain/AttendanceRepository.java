package refactor.domain;

import lombok.NonNull;

public interface AttendanceRepository {
    MonthlyAttendanceRecord findByYearMonth(@NonNull YearMonth yearMonth);

    void save(@NonNull DailyAttendanceRecord dailyAttendanceRecord);
}
