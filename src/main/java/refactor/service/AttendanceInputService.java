package refactor.service;

import refactor.domain.*;

import java.util.Objects;

public class AttendanceInputService {
    private final DailyAttendanceRecord dailyAttendanceRecord;
    private final AttendanceRepository repository;

    public AttendanceInputService(
            DailyAttendanceRecord dailyAttendanceRecord,
            AttendanceRepository repository) {
        this.dailyAttendanceRecord = Objects.requireNonNull(dailyAttendanceRecord);
        this.repository = Objects.requireNonNull(repository);
    }

    public void inputAttendance() {
        repository.save(dailyAttendanceRecord);
    }
}
