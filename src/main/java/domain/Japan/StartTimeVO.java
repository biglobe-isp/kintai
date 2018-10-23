package domain.Japan;

public class StartTimeVO {
    private final StartVO start;
    private final StartHourVO startHour;
    private final StartMinutesVO startMinutes;

    public StartTimeVO(StartVO start, StartHourVO startHour, StartMinutesVO startMinutes) {
        this.start = start;
        this.startHour = startHour;
        this.startMinutes = startMinutes;
    }

    public String getValue() {
        return start.getValue();
    }

    public int getHour() {
        return startHour.getValue();
    }

    public int getMinutes() {
        return startMinutes.getValue();
    }

    public int getTotalMinutes() {
        return startHour.getValue() * 60 + startMinutes.getValue();
    }
}
