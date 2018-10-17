package service;

public class StartTimeVO {
    private final int startTime;

    public StartTimeVO(StartHourVO startHour, StartMinutesVO startMinutes) {
        this.startTime = startHour.getStartHour() * 60 + startMinutes.getStartMinutes();
    }

    public int getStartTime() {
        return startTime;
    }
}
