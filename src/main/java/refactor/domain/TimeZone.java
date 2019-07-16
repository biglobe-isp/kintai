package refactor.domain;

import lombok.AllArgsConstructor;
import lombok.NonNull;

import java.util.Optional;

@AllArgsConstructor
public class TimeZone {
    @NonNull
    private final StartTime startTime;
    @NonNull
    private final EndTime endTime;

    public Optional<TimeZone> overlap(@NonNull TimeZone other) {
        if (isIncluded(other.startTime) && !isIncluded(other.endTime)) {
            return Optional.of(new TimeZone(other.startTime, endTime));
        }

        if (isIncluded(other.startTime) && isIncluded(other.endTime)) {
            return Optional.of(other);
        }

        if (!isIncluded(other.startTime) && isIncluded(other.endTime)) {
            return Optional.of(new TimeZone(startTime, other.endTime));
        }

        return Optional.empty();
    }

    private boolean isIncluded(@NonNull Time time) {
        return time.isLaterThanOrEqual(startTime) && endTime.isLaterThanOrEqual(time);
    }

    public int minutes() {
        return Math.abs(endTime.minutes() - startTime.minutes());
    }
}
