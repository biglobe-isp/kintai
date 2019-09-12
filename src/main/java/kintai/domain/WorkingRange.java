package kintai.domain;

import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.time.Duration;
import java.time.LocalTime;
import java.util.Optional;

@RequiredArgsConstructor
@EqualsAndHashCode
@ToString(includeFieldNames = false)
public class WorkingRange {

    private final TimeRange value;

    public WorkingRange(LocalTime from, LocalTime to) {
        value = new TimeRange(from, to);
    }

    public Duration overlapDuration(BreakRange breakRange) {
        return value.overlapDuration(breakRange.getValue());
    }

    public WorkingDuration workingDuration(Duration breakDuration) {
        return new WorkingDuration(value.minus(breakDuration));
    }
}
