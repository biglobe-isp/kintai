package kintai.domain;

import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@RequiredArgsConstructor
@EqualsAndHashCode
@ToString
public class AttendanceStartAndEndTime {

    private final LocalDate attendanceDate;
    private final LocalTime startTime;
    private final LocalTime endTime;

    public Attendance calculateTotalWorkingTime(EmployeeRule rule, LocalDateTime inputDateTime) {
        WorkingRange t = new WorkingRange(startTime, endTime);
        Duration totalBreakTime = rule.getBreakTimes().totalBreakTime(t);
        Duration workingDuration = t.duration().minus(totalBreakTime);
        Duration overWorkingDuration = workingDuration.minus(rule.getWorkHours());
        return new Attendance(attendanceDate, startTime, endTime, workingDuration, overWorkingDuration, inputDateTime);
    }
}
