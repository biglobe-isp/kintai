package kintai.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;

@RequiredArgsConstructor
@EqualsAndHashCode
@ToString
public class AttendanceStartAndEndTime {

    @Getter
    private final LocalDate attendanceDate;
    @Getter
    private final StartTime startTime;
    @Getter
    private final EndTime endTime;

    public WorkingRange range() {
        return new WorkingRange(startTime.getValue(), endTime.getValue());
    }
}
