package refactor.domain;

import lombok.AllArgsConstructor;
import lombok.NonNull;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@AllArgsConstructor
public class EndTime implements Time {
    private static final DateTimeFormatter END_TIME_FORMAT = DateTimeFormatter.ofPattern("HHmm");
    @NonNull
    private final LocalTime localTime;

    public static EndTime fromString(@NonNull String hhmm) {
        LocalTime localTime = LocalTime.parse(hhmm, END_TIME_FORMAT);
        return new EndTime(localTime);
    }

    public int getMinutes() {
        return localTime.getHour() * 60 + localTime.getMinute();
    }

    public boolean isLaterThanOrEqual(Time other) {
        return other.getMinutes() <= getMinutes();
    }

    @Override
    public String toString() {
        return localTime.format(END_TIME_FORMAT);
    }
}
