package kintai.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@RequiredArgsConstructor
@EqualsAndHashCode
@ToString
public class Attendance {

    @Getter
    private final LocalDate attendanceDate;

    @Getter
    private final StartTime startTime;

    @Getter
    private final EndTime endTime;

    @Getter
    private final WorkingDuration workingDuration;

    @Getter
    private final WorkingDuration overWorkingDuration;

    @Getter
    private final LocalDateTime inputDateTime;

    public Attendance(AttendanceStartAndEndTime attendanceStartAndEndTime, WorkingDuration workingDuration,
                      WorkingDuration overWorkingDuration, LocalDateTime now) {
        this(attendanceStartAndEndTime.getAttendanceDate(),
                attendanceStartAndEndTime.getStartTime(), attendanceStartAndEndTime.getEndTime(),
                workingDuration, overWorkingDuration, now);
    }

    public TotalWorkingTime totalWorkingTime() {
        return new TotalWorkingTime(workingDuration, overWorkingDuration);
    }
}
