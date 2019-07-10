package refactor.domain;

public class EndTime {
    private final int hour;
    private final int minute;

    public EndTime(int hour, int minute) {
        this.hour = hour;
        this.minute = minute;
    }

    public int inMinutes() {
        return hour * 60 + minute;
    }

    public int getHour() {
        return hour;
    }

    public int getMinute() {
        return minute;
    }

    public String inHHMM() {
        return String.format("%02d%02d", hour, minute);
    }
}
