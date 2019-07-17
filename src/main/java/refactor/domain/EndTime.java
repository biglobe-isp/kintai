package refactor.domain;

import lombok.NonNull;

public class EndTime implements Time {
    private final int hour;
    private final int minute;

    public EndTime(@NonNull String hhmm) {
        hour = Integer.valueOf(hhmm.substring(0, 2));
        minute = Integer.valueOf(hhmm.substring(2, 4));
    }

    public int minutes() {
        return hour * 60 + minute;
    }

    public boolean isLaterThanOrEqual(Time other) {
        return other.minutes() <= minutes();
    }

    @Override
    public String toString() {
        return String.format("%02d%02d", hour, minute);
    }
}
