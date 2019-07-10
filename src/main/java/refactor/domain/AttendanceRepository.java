package refactor.domain;

public interface AttendanceRepository {
    void save(DailyAttendanceRecord dailyAttendanceRecord);
}
