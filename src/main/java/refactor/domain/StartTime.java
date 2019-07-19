package refactor.domain;

import lombok.AllArgsConstructor;
import lombok.NonNull;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@AllArgsConstructor
public class StartTime implements Time {
    private static final DateTimeFormatter START_TIME_FORMAT = DateTimeFormatter.ofPattern("HHmm");
    @NonNull
    private final LocalTime localTime;

    public static StartTime fromString(@NonNull String hhmm) {
        LocalTime localTime = LocalTime.parse(hhmm, START_TIME_FORMAT);
        return new StartTime(localTime);
    }

    public int getMinutes() {
        return localTime.getHour() * 60 + localTime.getMinute();
    }

    public boolean isLaterThanOrEqual(@NonNull Time other) {
        return other.getMinutes() <= getMinutes();
    }

    @Override
    public String toString() {
        return localTime.format(START_TIME_FORMAT);
    }
}
