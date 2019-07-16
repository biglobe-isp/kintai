package refactor.domain;

import lombok.Getter;

public class EndTime {
    @Getter
    private final int hour;
    @Getter
    private final int minute;

    public EndTime(String hhmm) {
        hour = Integer.valueOf(hhmm.substring(0, 2));
        minute = Integer.valueOf(hhmm.substring(2, 4));
    }

    public int inMinutes() {
        return hour * 60 + minute;
    }

    public String inHHMM() {
        return String.format("%02d%02d", hour, minute);
    }
}
