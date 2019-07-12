package refactor.domain;

public class StartTime {
    private final int hour;
    private final int minute;

    public StartTime(String hhmm) {
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
