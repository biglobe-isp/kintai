package api;

public class Time {
    private final int hour;
    private final int minutes;

    public Time(String time) {
        this.hour = Integer.valueOf(time.substring(0, 2));
        this.minutes = Integer.valueOf(time.substring(0, 2));
    }

    public int getHour() {
        return hour;
    }

    public int getMinutes() {
        return minutes;
    }

    public int getTotalMinuts() {
        return hour * 60 + minutes;
    }
}
