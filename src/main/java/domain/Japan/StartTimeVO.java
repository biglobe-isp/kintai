package domain.Japan;

public class StartTimeVO {
    private final StartHourVO startHour;
    private final StartMinutesVO startMinutes;

    public StartTimeVO(String start) {
        this.startHour = new StartHourVO(Integer.valueOf(start));
        this.startMinutes = new StartMinutesVO(Integer.valueOf(start));
    }

    public int getHour() {
        return this.startHour.getValue();
    }

    public int getMinutes() {
        return this.startMinutes.getValue();
    }

    public int getTotalMinutes() {
        return startHour.getValue() * 60 + startMinutes.getValue();
    }
}
