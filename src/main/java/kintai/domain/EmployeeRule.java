package kintai.domain;

import lombok.*;

import java.time.Duration;
import java.time.LocalDateTime;

@RequiredArgsConstructor
@EqualsAndHashCode
@ToString
public class EmployeeRule {
    private final BreakRanges breakRanges;
    private final Duration workHours;

    public static EmployeeRule defaultEmployeeRule() {
        return new EmployeeRule(BreakRanges.builder()
                .append(12,0, 13, 0)
                .append(18, 0, 19, 0)
                .append(21, 0, 22, 0)
                .build(), Duration.ofHours(8));
    }

    public Attendance calculateTotalWorkingTime(AttendanceStartAndEndTime attendanceStartAndEndTime, LocalDateTime now) {
        WorkingDuration workingDuration = calculateWorkingDuration(attendanceStartAndEndTime, breakRanges);
        WorkingDuration overWorkingDuration = workingDuration.overWorkingDuration(workHours);
        return new Attendance(attendanceStartAndEndTime, workingDuration, overWorkingDuration, now);
    }

    public WorkingDuration calculateWorkingDuration(AttendanceStartAndEndTime attendanceStartAndEndTime, BreakRanges breakRanges) {
        WorkingRange workingRange = attendanceStartAndEndTime.range();
        Duration totalBreakDuration = breakRanges.getValues().stream()
                .map(b -> workingRange.overlapDuration(b))
                .reduce(Duration.ZERO, (sum, d) -> sum.plus(d));
        return workingRange.workingDuration(totalBreakDuration);
    }
}
