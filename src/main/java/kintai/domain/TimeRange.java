package kintai.domain;

import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.time.Duration;
import java.time.LocalTime;

@RequiredArgsConstructor
@EqualsAndHashCode
@ToString
public class TimeRange {
    private final LocalTime from;
    private final LocalTime to;

    public Duration overlapDuration(TimeRange other) {
        LocalTime from = this.from.isAfter(other.from) ? this.from : other.from;
        LocalTime to = this.to.isBefore(other.to) ? this.to : other.to;
        if (from.isAfter(to)) {
            return Duration.ZERO;
        }
        return Duration.between(from, to);
    }

    public Duration minus(Duration duration) {
        return Duration.between(from, to).minus(duration);
    }
}
