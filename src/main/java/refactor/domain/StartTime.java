package refactor.domain;

public class StartTime {
    private final int hour;
    private final int minute;

    public StartTime(int hour, int minute) {
        this.hour = hour;
        this.minute = minute;
    }

    public int inMinutes() {
        return hour * 60 + minute;
    }

    public String inHHMM() {
        return String.format("%02d%02d", hour, minute);
    }
}
