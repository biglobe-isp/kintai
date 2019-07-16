package refactor.service;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import refactor.domain.*;

@AllArgsConstructor
public class AttendanceInputService {
    @NonNull
    private final DailyAttendanceRecord dailyAttendanceRecord;
    @NonNull
    private final AttendanceRepository repository;

    public void inputAttendance() {
        repository.save(dailyAttendanceRecord);
    }
}
