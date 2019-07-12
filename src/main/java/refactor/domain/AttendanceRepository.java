package refactor.domain;

public interface AttendanceRepository {
    MonthlyAttendanceRecord findByYearMonth(YearMonth yearMonth);

    void save(DailyAttendanceRecord dailyAttendanceRecord);
}
