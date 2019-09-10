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
    private final LocalTime startTime;

    @Getter
    private final LocalTime endTime;

    @Getter
    private final Duration workingDuration;

    @Getter
    private final Duration overWorkingDuration;

    @Getter
    private final LocalDateTime inputDateTime;

    public TotalWorkingTime totalWorkingTime() {
        return new TotalWorkingTime(workingDuration, overWorkingDuration);
    }
}
